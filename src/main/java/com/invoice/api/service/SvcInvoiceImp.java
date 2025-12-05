package com.invoice.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.CartItemDto;
import com.invoice.api.dto.DtoInvoiceList;
import com.invoice.api.dto.DtoInvoiceRequest;
import com.invoice.api.dto.DtoProductOut; 
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.InvoiceItem;
import com.invoice.api.repository.RepoInvoice;
import com.invoice.api.repository.RepoInvoiceItem;
import com.invoice.common.mapper.MapperInvoiceList;
import com.invoice.common.util.JwtDecoder;
import com.invoice.exception.ApiException;
import com.invoice.exception.DBAccessException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SvcInvoiceImp implements SvcInvoice {

    @Autowired
    private RepoInvoice repo;

    @Autowired
    private RepoInvoiceItem repoItem;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private ClienteCustomerCarrito clienteCarrito;

    @Autowired
    private ClienteProduct clienteProduct;

    @Autowired
    MapperInvoiceList mapper;

    @Override
    public ResponseEntity<List<DtoInvoiceList>> getInvoices() {
        try {
            if(jwtDecoder.isAdmin()) {
                return new ResponseEntity<>(mapper.toDtoList(repo.findAll()), HttpStatus.OK);
            } else {
                Integer user_id = jwtDecoder.getUserId();
                return new ResponseEntity<>(mapper.toDtoList(repo.findByUser_Id(user_id)), HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            throw new DBAccessException();
        }
    }

    @Override
    public ResponseEntity<List<DtoInvoiceList>> getInvoices(Integer userId) {
        try {
            return new ResponseEntity<>(mapper.toDtoList(repo.findByUser_Id(userId)), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> createInvoice(DtoInvoiceRequest in, HttpServletRequest request) {
        try {
            List<CartItemDto> cartItems = clienteCarrito.findCarrito(in.getUser_id());

            if (cartItems == null || cartItems.isEmpty()) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "El carrito está vacío");
            }

            List<InvoiceItem> invoiceItems = new ArrayList<>();
            double subtotal = 0.0;

            for (CartItemDto item : cartItems) {
                
                ResponseEntity<DtoProductOut> prodResponse;
                try {
                    prodResponse = clienteProduct.getProduct(item.getProduct_id());
                } catch (Exception e) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "Error al obtener producto con ID: " + item.getProduct_id());
                }
                
                DtoProductOut product = prodResponse.getBody();
                
                if (product == null) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "El producto con ID " + item.getProduct_id() + " no existe");
                }
                
                if (product.getStock() < item.getCantidad()) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, 
                        "Stock insuficiente para: " + product.getProduct() + ". Disponible: " + product.getStock());
                }

                InvoiceItem invoiceItem = new InvoiceItem();
                invoiceItem.setProduct(product.getProduct());
                invoiceItem.setQuantity(item.getCantidad());
                invoiceItem.setUnit_price(product.getPrice()); 

                invoiceItems.add(invoiceItem);

                subtotal += invoiceItem.getUnit_price() * invoiceItem.getQuantity();
            }

            double taxes = subtotal * 0.16;
            double total = subtotal + taxes;

            Invoice invoice = new Invoice();
            invoice.setUser_id(in.getUser_id());
            invoice.setIssuer_name(in.getIssuer_name());
            invoice.setIssuer_pc(in.getIssuer_pc());
            invoice.setCustomer_rfc(in.getCustomer_rfc());
            invoice.setCustomer_name(in.getCustomer_name());
            invoice.setCurrency(in.getCurrency());
            invoice.setPayment_method(in.getPayment_method());
            invoice.setIssue_date(LocalDateTime.now().toString());
            invoice.setSubtotal(subtotal);
            invoice.setTaxes(taxes);
            invoice.setTotal(total);

            invoice = repo.save(invoice);

            for (InvoiceItem ii : invoiceItems) {
                ii.setInvoice(invoice);
                repoItem.save(ii);
            }

            for (CartItemDto item : cartItems) {
                clienteProduct.actualizaStockProduct(item.getProduct_id(), item.getCantidad());
            }

            clienteCarrito.deleteTodosCartItem(in.getUser_id());

            return new ResponseEntity<>(new ApiResponse("Factura generada exitosamente. Total: " + total), HttpStatus.CREATED);

        } catch (DataAccessException e) {
            throw new DBAccessException();
        }
    }
}
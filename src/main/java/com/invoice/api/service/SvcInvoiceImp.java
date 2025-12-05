package com.invoice.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.CartItemDto;
import com.invoice.api.dto.DtoInvoiceList;
import com.invoice.api.dto.DtoInvoiceRequest;
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.InvoiceItem;
import com.invoice.api.repository.RepoInvoice;
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
			}else {
				Integer user_id = jwtDecoder.getUserId();
				return new ResponseEntity<>(mapper.toDtoList(repo.findByUser_Id(user_id)), HttpStatus.OK);
			}
		}catch (DataAccessException e) {
	        throw new DBAccessException();
	    }
	}

	@Override
	public ResponseEntity<Invoice> getInvoice(Integer id) {
		try {
			Invoice invoice = repo.findById(id).get();
			if(!jwtDecoder.isAdmin()) {
				Integer user_id = jwtDecoder.getUserId();
				if(invoice.getUser_id() != user_id) {
					throw new ApiException(HttpStatus.FORBIDDEN, "El token no es válido para consultar esta factura");
				}
			}
			return new ResponseEntity<>(invoice, HttpStatus.OK);
		}catch (DataAccessException e) {
	        throw new DBAccessException();
	    }catch (NoSuchElementException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "El id de la factura no existe");
	    }
	}

	@Override
	public ResponseEntity<ApiResponse> createInvoice(DtoInvoiceRequest in, HttpServletRequest request) {
	    try {
	        List<CartItemDto> carrito = clienteCarrito.findCarrito(in.getUser_id());

	        if (carrito.isEmpty()) {
	            throw new ApiException(HttpStatus.BAD_REQUEST, "El carrito está vacío");
	        }

	        List<InvoiceItem> items = new ArrayList<>();

	        for (CartItemDto item : carrito) {
	            
	            Integer productId = item.getProduct_id(); 
	            
	            // Obtener precio del producto
	            ResponseEntity<Float> precioResponse = clienteProduct.getPrecio(productId);
	            Float precio = precioResponse.getBody();

	            // Actualizar stock
	            clienteProduct.actualizaStockProduct(productId, item.getCantidad());

	            // Crear item de factura
	            InvoiceItem invoiceItem = new InvoiceItem();
	            invoiceItem.setProduct(item.getProducto()); // nombre del producto
	            invoiceItem.setQuantity(item.getCantidad());
	            invoiceItem.setUnit_price(precio);

	            items.add(invoiceItem);
	        }

	        double subtotal = items.stream()
	                .mapToDouble(i -> i.getQuantity() * i.getUnit_price())
	                .sum();
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
	        invoice.setIssue_date(LocalDate.now().toString());
	        invoice.setSubtotal(subtotal);
	        invoice.setTaxes(taxes);
	        invoice.setTotal(total);

	        repo.save(invoice);

	        // Limpiar carrito después de crear la factura
	        clienteCarrito.deleteTodosCartItem(in.getUser_id());

	        return new ResponseEntity<>(new ApiResponse("Factura generada exitosamente"), HttpStatus.CREATED);

	    } catch (DataAccessException e) {
	        throw new DBAccessException();
	    }
	}
}

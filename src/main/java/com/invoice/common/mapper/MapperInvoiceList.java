package com.invoice.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.invoice.api.dto.DtoInvoiceList;
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.InvoiceItem;

@Service
public class MapperInvoiceList {

    public List<DtoInvoiceList> toDtoList(List<Invoice> invoices) {
        List<DtoInvoiceList> dtoInvoices = new ArrayList<>();

        for (Invoice invoice : invoices) {

            // Calcular total de items sumando la cantidad de cada item
            int totalItems = 0;
            if (invoice.getItems() != null) {
                totalItems = invoice.getItems().stream()
                    .mapToInt(InvoiceItem::getQuantity)
                    .sum();
            }

            DtoInvoiceList dtoInvoice = new DtoInvoiceList(
                    invoice.getId(),
                    invoice.getUser_id(),
                    invoice.getCustomer_rfc(),
                    invoice.getCustomer_name(),
                    invoice.getIssue_date(),
                    invoice.getTotal(),
                    totalItems,
                    invoice.getPayment_method(),   
                    invoice.getShipping_address()  
                );
            dtoInvoices.add(dtoInvoice);
        }

         return dtoInvoices;
    }
}
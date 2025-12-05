package com.invoice.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.DtoInvoiceList;
import com.invoice.api.dto.DtoInvoiceRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface SvcInvoice {

    
    public ResponseEntity<List<DtoInvoiceList>> getInvoices();

    
    public ResponseEntity<List<DtoInvoiceList>> getInvoices(Integer userId);

    public ResponseEntity<ApiResponse> createInvoice(DtoInvoiceRequest in, HttpServletRequest request);

   
}
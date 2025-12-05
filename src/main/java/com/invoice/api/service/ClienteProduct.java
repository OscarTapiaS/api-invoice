package com.invoice.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.DtoProductOut;

@FeignClient(name = "api-product", url = "http://localhost:8080")
public interface ClienteProduct {
    
    // Obtener el producto completo por ID
    @GetMapping("/product/{id}")
    ResponseEntity<DtoProductOut> getProduct(@PathVariable("id") Integer id);
    
    @GetMapping("/product/{id}/precio")
    ResponseEntity<Float> getPrecio(@PathVariable("id") Integer id);
    
    // Si necesitas actualizar stock, debes crear este endpoint en CtrlProduct
    @PatchMapping("/product/{id}/stock/{cantidad}")
    ResponseEntity<ApiResponse> actualizaStockProduct(
        @PathVariable("id") Integer id,
        @PathVariable("cantidad") Integer cantidad
    );
}
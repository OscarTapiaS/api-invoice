package com.invoice.api.service;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.CartItemDto;

@FeignClient(name = "api-customer", url = "http://localhost:8082")
public interface ClienteCustomerCarrito {
    
    @GetMapping("/cart-item/{user_id}")
    List<CartItemDto> findCarrito(@PathVariable("user_id") Integer userId);
    
    @DeleteMapping("/cart-item/{user_id}")
    ResponseEntity<ApiResponse> deleteTodosCartItem(@PathVariable("user_id") Integer userId);
}

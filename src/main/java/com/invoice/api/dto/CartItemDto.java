package com.invoice.api.dto;

public class CartItemDto {
    
    private Integer cart_id;
    private Integer user_id;
    private Integer product_id;
    private String producto;
    private Integer cantidad;
    
    // Getters y Setters
    public Integer getCart_id() {
        return cart_id;
    }
    
    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }
    
    public Integer getUser_id() {
        return user_id;
    }
    
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    
    public Integer getProduct_id() {
        return product_id;
    }
    
    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }
    
    public String getProducto() {
        return producto;
    }
    
    public void setProducto(String producto) {
        this.producto = producto;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
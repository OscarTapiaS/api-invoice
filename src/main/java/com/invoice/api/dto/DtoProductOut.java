package com.invoice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DtoProductOut {

    @JsonProperty("product_id")
    private Integer product_id;

    @JsonProperty("gtin")
    private String gtin;

    @JsonProperty("product")
    private String product; // Nombre del producto

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("category_id")
    private Integer category_id;

    @JsonProperty("category")
    private String category;

    @JsonProperty("status")
    private Integer status;

    // Getters y Setters
    public Integer getProduct_id() { return product_id; }
    public void setProduct_id(Integer product_id) { this.product_id = product_id; }

    public String getGtin() { return gtin; }
    public void setGtin(String gtin) { this.gtin = gtin; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Integer getCategory_id() { return category_id; }
    public void setCategory_id(Integer category_id) { this.category_id = category_id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
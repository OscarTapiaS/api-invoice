package com.invoice.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "invoice_item")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_item_id")
    private Integer invoice_item_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
    private Invoice invoice;

    @NotNull(message="El product es obligatorio")
    @Column(name = "product")
    private String product;

    @NotNull(message="El quantity es obligatorio")
    @Column(name = "quantity")
    private Integer quantity;

    @NotNull(message="El unit_price es obligatorio")
    @Column(name = "unit_price")
    private Double unit_price; 

    // Getters y Setters
    public Integer getInvoice_item_id() {
        return invoice_item_id;
    }

    public void setInvoice_item_id(Integer invoice_item_id) {
        this.invoice_item_id = invoice_item_id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }
}

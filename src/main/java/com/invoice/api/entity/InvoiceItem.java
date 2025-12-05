package com.invoice.api.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class InvoiceItem {
	
	@NotNull(message="El product es obligatorio")
	private String product;

	@NotNull(message="El quantity es obligatorio")
	private Integer quantity;

	@NotNull(message="El unit_price es obligatorio")
	private Float unit_price;

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

	public Float getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Float unit_price) {
		this.unit_price = unit_price;
	}


}

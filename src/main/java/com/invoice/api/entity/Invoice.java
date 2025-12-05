package com.invoice.api.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	private Integer id;

	@Column(name = "user_id")
	private Integer user_id;

	@Column(name = "issuer_name")
	private String issuer_name;

	@Column(name = "issuer_pc")
	private String issuer_pc;

	@Column(name = "customer_rfc")
	private String customer_rfc;

	@Column(name = "customer_name")
	private String customer_name;

	@Column(name = "issue_date")
	private String issue_date;

	@Column(name = "currency")
	private String currency;

	@Column(name = "payment_method")
	private String payment_method;

	@Column(name = "subtotal")
	private Double subtotal;

	@Column(name = "taxes")
	private Double taxes;

	@Column(name = "total")
	private Double total;
	
	
	//@ElementCollection
	//@CollectionTable(name = "invoice_items", joinColumns = @JoinColumn(name = "invoice_id"))
	//private List<InvoiceItem> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getIssuer_name() {
		return issuer_name;
	}

	public void setIssuer_name(String issuer_name) {
		this.issuer_name = issuer_name;
	}

	public String getIssuer_pc() {
		return issuer_pc;
	}

	public void setIssuer_pc(String issuer_pc) {
		this.issuer_pc = issuer_pc;
	}

	public String getCustomer_rfc() {
		return customer_rfc;
	}

	public void setCustomer_rfc(String customer_rfc) {
		this.customer_rfc = customer_rfc;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getTaxes() {
		return taxes;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	/*public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}*/

}

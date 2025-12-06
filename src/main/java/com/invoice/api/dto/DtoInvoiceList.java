package com.invoice.api.dto;

public class DtoInvoiceList {
	
	private Integer id;
	private Integer user_id;
	private String customer_rfc;
	private String customer_name;
	private String issue_date;
	private Double total_price;
	private Integer total_items;
	private String payment_method;   
	private String shipping_address; 

	public DtoInvoiceList(Integer id, Integer user_id, String customer_rfc, String customer_name, String issue_date,
			Double total_price, Integer total_items, String payment_method, String shipping_address) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.customer_rfc = customer_rfc;
		this.customer_name = customer_name;
		this.issue_date = issue_date;
		this.total_price = total_price;
		this.total_items = total_items;
		this.payment_method = payment_method;     
		this.shipping_address = shipping_address; 
	}

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

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public Integer getTotal_items() {
		return total_items;
	}

	public void setTotal_items(Integer total_items) {
		this.total_items = total_items;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}
}
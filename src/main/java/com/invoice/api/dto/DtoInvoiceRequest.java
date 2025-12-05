package com.invoice.api.dto;

public class DtoInvoiceRequest {
	
	
	  private Integer user_id;
	    private String issuer_name;
	    private String issuer_pc;
	    private String customer_rfc;
	    private String customer_name;
	    private String currency;
	    private String payment_method;
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
	    
	    
	
}

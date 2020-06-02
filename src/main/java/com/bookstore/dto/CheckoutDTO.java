package com.bookstore.dto;

import com.bookstore.domain.BillingAddress;
import com.bookstore.domain.Payment;
import com.bookstore.domain.ShippingAddress;

public class CheckoutDTO {

	ShippingAddress shippingAddress;
	BillingAddress billingAddress;
	Payment payment;
	String shippingMethod;
	
	
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public BillingAddress getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public String getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	
	
	
}

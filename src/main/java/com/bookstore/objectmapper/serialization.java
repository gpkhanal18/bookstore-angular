package com.bookstore.objectmapper;

import com.bookstore.domain.BillingAddress;
import com.bookstore.domain.Payment;
import com.bookstore.domain.ShippingAddress;
import com.bookstore.dto.CheckoutDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class serialization {
	
	
	
	
	public static void main(String[] args) throws JsonProcessingException {
//		Book book = new Book(); 
//		ObjectMapper mapper = new ObjectMapper();
//		String writeValueAsString = mapper.writeValueAsString(book);
//		System.out.println(writeValueAsString);
		
		
		CheckoutDTO checkoutDTO = new CheckoutDTO();
		BillingAddress ba = new BillingAddress(); 
		ba.setBillingAddressCity("denver");
		ShippingAddress sa = new ShippingAddress(); 
		sa.setShippingAddressCity("denplusver");
		Payment pa = new Payment();
		pa.setCardNumber("12345");
		checkoutDTO.setBillingAddress(ba);
		checkoutDTO.setShippingAddress(sa);
		checkoutDTO.setPayment(pa);
		
		System.out.println(new ObjectMapper().writeValueAsString(checkoutDTO));
	}

}

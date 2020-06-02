package com.bookstore.objectmapper;

import com.bookstore.domain.UserShipping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class serialization {
	
	
	
	
	public static void main(String[] args) throws JsonProcessingException {
//		Book book = new Book(); 
//		ObjectMapper mapper = new ObjectMapper();
//		String writeValueAsString = mapper.writeValueAsString(book);
//		System.out.println(writeValueAsString);
		
		
		System.out.println(new ObjectMapper().writeValueAsString(new UserShipping()));
	}

}

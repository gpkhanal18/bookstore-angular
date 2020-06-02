package com.bookstore.dto;

import javax.validation.constraints.NotNull;

public class UserDto {
	
	private Long id; 
	
	@NotNull(message = "username must not be null")
	private String username; 
	@NotNull(message = "password must not be null")
	private String password;
	@NotNull(message = "email must not be null")
	private String email;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 

	
	
}

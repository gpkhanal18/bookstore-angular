package com.bookstore.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.domain.User;
import com.bookstore.domain.UserShipping;
import com.bookstore.service.UserService;
import com.bookstore.service.UserShippingService;

@RestController
@RequestMapping("/shipping")
public class ShippingResource {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserShippingService userShippingService;
	
	@RequestMapping(value="/add/{userId}", method=RequestMethod.POST)
	public ResponseEntity addNewUserShippingPost(
			@RequestBody UserShipping userShipping, @PathVariable Long userId
			) {
		User user = userService.findById(userId);
		userService.updateUserShipping(userShipping, user);
		
		List<UserShipping> userShippingList = userService.findById(userId).getUserShippingList();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userShippingList);
//		return new ResponseEntity("Shipping Added(Updated) Successfully!", HttpStatus.OK);
	}
	
	@RequestMapping("/{userId}/getUserShippingList")
	public List<UserShipping> getUserShippingList(
			@PathVariable Long userId
			){
		User user = userService.findById(userId);
		List<UserShipping> userShippingList = user.getUserShippingList();
		
		return userShippingList;
	}
	
	@RequestMapping(value="/{userId}/remove/{shippingId}", method=RequestMethod.DELETE)
	public ResponseEntity removeUserShippingPost(
			@PathVariable Long userId, @PathVariable Long shippingId
			) {
		userShippingService.removeById(shippingId);
		
		List<UserShipping> userShippingList = userService.findById(userId).getUserShippingList();
		
		return ResponseEntity.status(HttpStatus.OK).body(userShippingList);
//		return new ResponseEntity("Shipping Removed Successfully!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/{userId}/payment/{shippingId}/setDefault", method=RequestMethod.PUT)
	public ResponseEntity setDefaultUserShippingPost(
			@PathVariable Long userId, @PathVariable Long shippingId
			){
		User user = userService.findById(userId);
		userService.setUserDefaultShipping(shippingId, user);
		
		List<UserShipping> userShippingList = userService.findById(userId).getUserShippingList();
		
		return ResponseEntity.status(HttpStatus.OK).body(userShippingList);
//		return new ResponseEntity("Set Default Shipping Successfully!", HttpStatus.OK);
	}
}

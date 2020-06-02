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
import com.bookstore.domain.UserBilling;
import com.bookstore.domain.UserPayment;
import com.bookstore.service.UserPaymentService;
import com.bookstore.service.UserService;

@RestController
@RequestMapping("/payment")
public class PaymentResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPaymentService userPaymentService;

	@RequestMapping(value="/add/{userId}", method=RequestMethod.POST)
	public ResponseEntity<List<UserPayment>> addNewCreditCardPost (
			@RequestBody UserPayment userPayment,@PathVariable Long userId) {
		User user = userService.findById(userId);
		
		UserBilling userBilling = userPayment.getUserBilling();
		
		User updateUser = userService.updateUserBilling(userBilling, userPayment, user);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(updateUser.getUserPaymentList());
	}
	
	@RequestMapping(value="/user/{userId}/payment/{paymentId}/remove", method=RequestMethod.DELETE)
	public ResponseEntity removePayment(@PathVariable Long userId, @PathVariable int paymentId){
//		User user = userService.findByUsername(principal.getName());
		
		userPaymentService.removeById(Long.valueOf(paymentId));
		// get updated paymenet list 
		List<UserPayment> userPaymentList = userService.findById(userId).getUserPaymentList();
		
		return ResponseEntity.status(HttpStatus.OK).body(userPaymentList);
//		return new ResponseEntity("Payment Removed Successfully!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/{userId}/payment/{paymentId}/setDefault", method=RequestMethod.PUT)
	public ResponseEntity setDefaultPaymentPost(@PathVariable int userId, @PathVariable Long paymentId){
		User user = userService.findById((long) userId);
		
		userService.setUserDefaultPayment(paymentId, user);
		// user new payment list 
		List<UserPayment> userPaymentList = userService.findById((long) userId).getUserPaymentList();
		
		return ResponseEntity.status(HttpStatus.OK).body(userPaymentList);
		
//		return new ResponseEntity("Defauly!", HttpStatus.OK);
	}
	
	@RequestMapping("/getUserPaymentList/{userId}")
	public List<UserPayment> getUserPaymentList(@PathVariable Long userId){
		User user = userService.findById(userId);
		
		List<UserPayment> userPaymentList = user.getUserPaymentList();
		
		return userPaymentList;
	}
	
}

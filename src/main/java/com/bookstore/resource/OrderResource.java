package com.bookstore.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.domain.Order;
import com.bookstore.domain.User;
import com.bookstore.service.OrderService;
import com.bookstore.service.UserService;

@RestController
@RequestMapping("/order")
public class OrderResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService; 
	
	@RequestMapping("/{userId}/getOrderList")
	public List<Order> getOrderList(@PathVariable Long userId) {
		User user = userService.findById(userId);
		List<Order> orderList = user.getOrderList();
		
		return orderList;
	}
	
	@RequestMapping("/{userId}/getOrderList/{orderId}")
	public Order getOrder(@PathVariable Long userId,
			@PathVariable Long orderId
			) {
//		User user = userService.findById(userId);
//		List<Order> orderList = user.getOrderList();
		return orderService.findOne(orderId);
		
//		return orderList;
	}

}

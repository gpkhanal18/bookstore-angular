package com.bookstore.resource;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.domain.Book;
import com.bookstore.domain.CartItem;
import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.User;
import com.bookstore.dto.AddItemDto;
import com.bookstore.dto.CartItemRequestDto;
import com.bookstore.service.BookService;
import com.bookstore.service.CartItemService;
import com.bookstore.service.ShoppingCartService;
import com.bookstore.service.UserService;

@RestController
@RequestMapping("/cart")
public class ShoppingCartResource {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping(value="/{userId}/add", method=RequestMethod.POST)
	public ResponseEntity addItem (
			@RequestBody AddItemDto addItemDto, @PathVariable Long userId
			){
		String bookId = addItemDto.getBookId();
		String qty = addItemDto.getQty();
		
		User user = userService.findById(userId);
		Book book = bookService.findOne(Long.parseLong(bookId));
		
		if(Integer.parseInt(qty) > book.getInStockNumber()) {
			return new ResponseEntity("Not Enough Stock!", HttpStatus.BAD_REQUEST);
		}
		
		CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty));
		
		return new ResponseEntity("Book Added Successfully!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/{userId}/getCartItemList", method=RequestMethod.GET)
	public List<CartItem> getCartItemList(@PathVariable Long userId) {
		User user = userService.findById(userId);
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		return cartItemList;
	}
	
	@RequestMapping(value="/{userId}/getShoppingCart", method=RequestMethod.GET)
	public ShoppingCart getShoppingCart(@PathVariable Long userId) {
		User user = userService.findById(userId);
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		return shoppingCart;
	}
	
	@RequestMapping(value="/removeItem/{itemId}", method=RequestMethod.DELETE)
	public ResponseEntity removeItem(@PathVariable Long itemId) {
		cartItemService.removeCartItem(cartItemService.findById(itemId));
		
		return new ResponseEntity("Cart Item Removed Successfully!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateCartItem", method=RequestMethod.PUT)
	public ResponseEntity updateCartItem(
			@RequestBody CartItemRequestDto cartItemRequestDto
			){
		String cartItemId = cartItemRequestDto.getCartItemId();
		String qty = cartItemRequestDto.getQty();
		
		CartItem cartItem = cartItemService.findById(Long.parseLong(cartItemId));
		cartItem.setQty(Integer.parseInt(qty));
		
		cartItemService.updateCartItem(cartItem);
		
		return new ResponseEntity("Cart Item Updated Successfully!", HttpStatus.OK);
	}
	
}

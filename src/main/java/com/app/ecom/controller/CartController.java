package com.app.ecom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecom.dto.CarItemRequest;
import com.app.ecom.models.CartItem;
import com.app.ecom.services.CartItemService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public  class CartController {
	
	private final CartItemService cartItemService;

	@PostMapping
	public ResponseEntity<String>  addToCart(
			@RequestHeader("X-User-ID") String userId,
			@RequestBody CarItemRequest request){
		   if(!cartItemService.addToCart(userId,request)) {
			   return ResponseEntity.badRequest().body("Product not foud");
		   }
		 
	       return ResponseEntity.status(HttpStatus.CREATED).build();
		   }
	
	@DeleteMapping("/item/{productId}")
	public ResponseEntity<Void> removeFromCart(
			@RequestHeader("X-User-ID") String userId,
			@PathVariable Long productId){
		boolean deleted = cartItemService.deleteItemFromCart(userId,productId);
		return deleted? ResponseEntity.noContent().build():ResponseEntity.notFound().build();
	}
	
	/*
	 * @GetMapping public ResponseEntity<List<CartItem>>
	 * getCartItems(@RequestHeader("X-User-ID") String userId){ return
	 * ResponseEntity.ok(cartItemService.getCartItem(userId));
	 * 
	 * }
	 */
	
	@GetMapping
	public ResponseEntity<List<CartItem>> getCartItems(
	        @RequestHeader("X-User-ID") String userId) {

	    return ResponseEntity.ok(
	            cartItemService.getCartItem(userId)
	    );
	}
	
	
}

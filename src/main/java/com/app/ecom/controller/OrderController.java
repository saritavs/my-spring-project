package com.app.ecom.controller;

import java.net.http.HttpResponse;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecom.dto.OrderResponse;
import com.app.ecom.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(
				@RequestHeader("X-User-ID") String userId){
		
		/*Optional<OrderResponse> order = orderService.CreateOrder(userId);
		return new ResponseEntity<OrderResponse>(HttpStatus.CREATED);
		*/
		return orderService.CreateOrder(userId)
				.map(orderresponse -> new ResponseEntity<>(orderresponse,HttpStatus.CREATED))
				.orElseGet(() ->ResponseEntity.badRequest().build());
	}

}

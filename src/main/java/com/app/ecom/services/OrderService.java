package com.app.ecom.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.ecom.dto.OrderItemDTO;
import com.app.ecom.dto.OrderResponse;
import com.app.ecom.enums.OrderStatus;
import com.app.ecom.models.CartItem;
import com.app.ecom.models.Order;
import com.app.ecom.models.OrderItem;
import com.app.ecom.models.User;
import com.app.ecom.repositories.OrderRepository;
import com.app.ecom.repositories.UserRepositories;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service

@AllArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepo;
	private final CartItemService cartService;
	private UserRepositories userRepo;

	public Optional<OrderResponse> CreateOrder(String userId) {
		// TODO Auto-generated method stub
		
		//Validate Cart items
		
		
		List<CartItem> cartItems = cartService.getCartItem(userId);
		
		if(cartItems.isEmpty()) {
			Optional.empty();
		}
		
		//Validate User
		Optional<User> userOpt = userRepo.findById(Long.valueOf(userId));
		
		if(userOpt.isEmpty()) {
			Optional.empty();
		}
		
		User user =  userOpt.get();
		
		//Calculate total price;
		
		BigDecimal totalPrice =  cartItems.stream()
				            .map(CartItem::getPrice)
				            .reduce(BigDecimal.ZERO,BigDecimal::add);
		
		
		Order order = new Order();
		
		List<OrderItem> orderItems = cartItems.stream()
				.map(item -> new OrderItem(
						null,
						item.getProduct(),
						item.getQuantity(),
						item.getPrice(),
						order
						)).toList();
		order.setItems(orderItems);
		
		order.setUser(user);
		order.setStatus(OrderStatus.CONFIMRED);
		order.setTotalAmount(totalPrice);
		Order savedOrder = orderRepo.save(order);
		
		return Optional.of(mapToOrderResponse(savedOrder));
	}

	private OrderResponse mapToOrderResponse(Order order) {
		return new OrderResponse(
	            order.getId(),
	            order.getTotalAmount(),
	            order.getStatus(),

	            order.getItems().stream()
	                    .map(item -> new OrderItemDTO(
	                            item.getId(),
	                            item.getProduct(),
	                            item.getQuantity(),
	                            item.getPrice(),
	                            item.getPrice()
	                                    .multiply(BigDecimal.valueOf(item.getQuantity()))
	                    ))
	                    .toList()
	    );
		
	}

}

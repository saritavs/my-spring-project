package com.app.ecom.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.app.ecom.enums.OrderStatus;
import com.app.ecom.models.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class OrderResponse {
	
	private Long Id;
	private BigDecimal totalAmount;
	private OrderStatus status;
	private List<OrderItemDTO> items;
	//private LocalDateTime createdAT;

}

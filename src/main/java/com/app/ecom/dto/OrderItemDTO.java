package com.app.ecom.dto;

import java.math.BigDecimal;

import com.app.ecom.models.Products;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDTO {
	
	private Long Id;
	private Products productId;
	private Integer quantity;
	private BigDecimal 	price;
	private BigDecimal subTotal;
	

}

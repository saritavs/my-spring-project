package com.app.ecom.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductRequest {
	
	private String name;
	private String description;
	private String imageUrl;
	private int stockQuantity;
	private BigDecimal price;
	private String category;
	

}

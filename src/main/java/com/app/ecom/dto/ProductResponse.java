package com.app.ecom.dto;

import java.math.BigDecimal;

import lombok.Data;


@Data

public class ProductResponse {
	
	private Long id;
	private String name;
	private String descrption;
	private String ImageUrl;
	private int stockQuantity;
	private BigDecimal price;
	private String category;
	private Boolean active;

}

package com.app.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity(name ="addresses")
@Data
@NoArgsConstructor
public class Addresses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	
	
	
	

}

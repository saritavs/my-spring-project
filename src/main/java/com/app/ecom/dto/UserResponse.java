package com.app.ecom.dto;

import com.app.ecom.models.UserRole;

import lombok.Data;


@Data
public class UserResponse {
	
	private String id;
	private String firstName;
	private String lastName;
	
	private String email;
	private String phoneNum;
	private UserRole role;
	private AdressDto adress;
	
	

}

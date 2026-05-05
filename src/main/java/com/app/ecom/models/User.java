package com.app.ecom.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//With specific custom table name : @Entity(name="UserTable")
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name="User_Table")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	
	private String email;
	private String phoneNum;
	private UserRole role=UserRole.CUSTOMER;
	
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval =true)	
	@JoinColumn(name ="address_id",referencedColumnName = "Id")
	private Addresses address;
	

	
	

}

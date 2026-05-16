package com.app.ecom.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
	//private List<User> userList = new ArrayList<User>();
	/**
	 * There are three ways to inject class
	 * 1 Autowired
	 * 2. Create constructor
	 * 3. @RequiredArgsConstructor of lombark
	 * @return
	 */
	
	private final UserService userservice;
	
	@GetMapping
	 public ResponseEntity<List<UserResponse>> getAllUsers(){
		   
		  return ResponseEntity.ok(userservice.getAllUsers());
		 
		 
	 }
	
	@GetMapping("/{id}")
	 public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
		//Optional<User> user = userservice.getUser(id);
		/*
		 * if(user == null) { //return ResponseEntity.notFound().build(); return
		 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"); } return
		 * ResponseEntity.ok(user);
		 */
		  
		  return userservice.getUser(id)
				  .map(ResponseEntity::ok)
				  .orElseGet(() -> ResponseEntity.notFound().build());
		 
		 
	 }
	
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
		//userList.add(user);
	       userservice.createUser(userRequest);
	       return ResponseEntity.ok("User added successfull");
		
		
	}
	
	@PutMapping("/update/{id}")
	 public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UserRequest userRequest){
				  
         Boolean Message = userservice.updateUser(id, userRequest);
         if(Message) {
        	   return ResponseEntity.ok("User updated successfully");
        			   
         }
		 return ResponseEntity.notFound().build();
		 
	 }
	
	

}

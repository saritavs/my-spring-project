package com.app.ecom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 public ResponseEntity<List<User>> getAllUsers(){
		   
		  return ResponseEntity.ok(userservice.getAllUsers());
		 
		 
	 }
	
	@GetMapping("/{id}")
	 public ResponseEntity<User> getUser(@PathVariable Long id){
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
	public ResponseEntity<List<User>> createUser(@RequestBody User user){
		//userList.add(user);
		return ResponseEntity.ok(userservice.createUser(user));
		
		
	}
	
	@PutMapping("/update/{id}")
	 public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User newUser){
				  
         String Message = userservice.updateUser(id, newUser);
		 return ResponseEntity.ok().build(); 
		 
	 }
	
	

}

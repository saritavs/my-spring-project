package com.app.ecom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
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
	
	@GetMapping("api/users")
	 public List<User> getAllUsers(){
		  return userservice.getAllUsers();	  
		 
		 
	 }
	
	@PostMapping("api/users")
	public String createUser(@RequestBody User user){
		//userList.add(user);
		userservice.createUser(user);
		return "User Created";
		
	}

}

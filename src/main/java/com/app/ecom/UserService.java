package com.app.ecom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service

public class UserService {
	private Long id = 1l;
	
	private List<User> userList = new ArrayList<User>();
	

		public List<User> getAllUsers(){
			return userList;
			
		}
	 
		public List<User> createUser(User user){
			user.setId(id);
			id++;
			userList.add(user);
			return userList;
		}
		
		public String updateUser(Long Id,User newUser){
			 userList.stream()
			.filter(user -> user.getId().equals(Id))
			.findFirst()
			.ifPresent(user ->{
				user.setFirstName(newUser.getFirstName());
				user.setLastName(newUser.getLastName());
				
			});
			 
			
		 return "User updated ";
			
			
			
		}
		
		public Optional<User> getUser(Long Id) {
			
			return userList.stream()
					.filter(user -> user.getId().equals(Id))
					.findFirst();
			
			/*
			 * for(User user: userList) { if(user.getId().equals(Id)) { return user; } }
			 * return null;
			 */
		}
	 
	 
}
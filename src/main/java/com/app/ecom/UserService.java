package com.app.ecom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service

public class UserService {
	
	private List<User> userList = new ArrayList<User>();
	

		public List<User> getAllUsers(){
			return userList;
			
		}
	 
		public List<User> createUser(User user){
			userList.add(user);
			return userList;
		}
	 
	 
}
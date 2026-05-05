package com.app.ecom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.MediaSize.Other;

import org.springframework.stereotype.Service;

import com.app.ecom.dto.AdressDto;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.models.User;
import com.app.ecom.repositories.UserRepositories;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private Long id = 1l;
	
	//private List<User> userList = new ArrayList<User>();
	private final UserRepositories userRepo;

	/*
	 * public List<User> getAllUsers(){ return userList;
	 * 
	 * }
	 * 
	 * 
	 */
	public List<UserResponse> getAllUsers(){ 
		return userRepo.findAll();
	
	
	}
	/*
	 * public List<User> createUser(User user){ user.setId(id); id++;
	 * userList.add(user); return userList; }
	 */
	
	 public void createUser(User user){
		 userRepo.save(user);
		 
	 }
		
		public Boolean updateUser(Long Id,User newUser){
			/*
			 * userList.stream() .filter(user -> user.getId().equals(Id)) .findFirst()
			 * .ifPresent(user ->{ user.setFirstName(newUser.getFirstName());
			 * user.setLastName(newUser.getLastName());
			 * 
			 * });
			 */
			
			return userRepo.findById(Id)
					.map(exisitinguser ->{
				     exisitinguser.setFirstName(newUser.getFirstName());
				     exisitinguser.setLastName(newUser.getLastName());
				     userRepo.save(exisitinguser);
				     return true;
				
			}).orElse(false);
			
		}
		
		public Optional<User> getUser(Long Id) {
			return userRepo.findById(Id);
			
			/*
			 * return userList.stream() .filter(user -> user.getId().equals(Id))
			 * .findFirst();
			 */
			
			/*
			 * for(User user: userList) { if(user.getId().equals(Id)) { return user; } }
			 * return null;
			 */
		}
		
		
		private UserResponse mapToUserReponse(User user) {
			
			UserResponse response = new UserResponse();
			response.setId(String.valueOf(user.getId()));
			response.setFirstName(user.getFirstName());
			response.setLastName(user.getLastName());
			response.setEmail(user.getEmail());
			response.setPhoneNum(user.getPhoneNum());
			response.setRole(user.getRole());
			
			if(user.getAddress()!= null) {
				AdressDto addressDto = new AdressDto();
				addressDto.setCity(user.getAddress().getCity());
				addressDto.setCountry(user.getAddress().getCountry());
				addressDto.setState(user.getAddress().getState());
				addressDto.setZipcode(user.getAddress().getZipcode());
				response.setAdress(addressDto);
			}
			
			return response;
			
		}
	 
	 
}
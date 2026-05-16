package com.app.ecom.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.ecom.dto.AdressDto;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.models.Addresses;
import com.app.ecom.models.User;
import com.app.ecom.repositories.UserRepositories;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
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
		
		return userRepo.findAll().stream()
				.map(this::mapToUserReponse)
				.collect(Collectors.toList());
	
	
	}
	/*
	 * public List<User> createUser(User user){ user.setId(id); id++;
	 * userList.add(user); return userList; }
	 */
	
	 public void createUser(UserRequest userRequest){
		 User user = new User();
		 updateUserFromRequest(user,userRequest);
		 userRepo.save(user);
		 
	 }
		
		public Boolean updateUser(Long Id,UserRequest userRequest){
			/*
			 * userList.stream() .filter(user -> user.getId().equals(Id)) .findFirst()
			 * .ifPresent(user ->{ user.setFirstName(newUser.getFirstName());
			 * user.setLastName(newUser.getLastName());
			 * 
			 * });
			 */
			
			return userRepo.findById(Id)
					.map(exisitinguser -> {
				     updateUserFromRequest(exisitinguser, userRequest);
				     userRepo.save(exisitinguser);
				     return true;
				
			}).orElse(false);
			
										
		}
		
		public Optional<UserResponse> getUser(Long Id) {
			return userRepo.findById(Id)
					.map(this::mapToUserReponse);
			
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
	 
		
		private void updateUserFromRequest(User user, UserRequest request) {
			user.setFirstName(request.getFirstName());
			user.setLastName(request.getLastName());
			user.setEmail(request.getEmail());
			user.setPhoneNum(request.getPhoneNum());
			
			if(request.getAddress()!= null) {
				Addresses address = new Addresses();
				address.setCity(request.getAddress().getCity());
				address.setCity(request.getAddress().getCountry());
				address.setState(request.getAddress().getState());
				address.setStreet(request.getAddress().getStreet());
				address.setZipcode(request.getAddress().getZipcode());
				
				user.setAddress(address);
						
			}
		}

		
}
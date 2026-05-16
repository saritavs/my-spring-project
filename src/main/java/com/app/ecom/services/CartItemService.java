package com.app.ecom.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.app.ecom.dto.CarItemRequest;
import com.app.ecom.models.CartItem;
import com.app.ecom.models.Products;
import com.app.ecom.models.User;
import com.app.ecom.repositories.CartItemRepository;
import com.app.ecom.repositories.ProductsRepositories;
import com.app.ecom.repositories.UserRepositories;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemService {
	
	private final ProductsRepositories prodRepo;
	private  final UserRepositories userRepo;
	private final CartItemRepository cartRepo;

	public boolean addToCart(String userId, CarItemRequest request) {
		// TODO Auto-generated method stub
		Optional<Products> productOpt = prodRepo.findById(request.getProductId());
		if(productOpt.isEmpty()) {
			return false;
		}
		
		Products product = productOpt.get();
		if(product.getStockQuantity() < request.getQuantity()){
			return false;
		}
		
		Optional<User> userOpt = userRepo.findById(Long.valueOf(userId));
		if(userOpt.isEmpty()) {
			return false;
		}
		
		User user = userOpt.get();
		CartItem existingCartItem = cartRepo.findByUserAndProduct(user, product);
			if(existingCartItem!=null) {
				//Update the quantity
				existingCartItem.setQuantity(existingCartItem.getQuantity()+ request.getQuantity());
				existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
				cartRepo.save(existingCartItem);
			}else {
				CartItem newItem = new CartItem();
				newItem.setProduct(product);
				newItem.setUser(user);
				newItem.setQuantity(request.getQuantity());
				newItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
				cartRepo.save(newItem);
				
			}
			
			return true;
		
	}

	public boolean deleteItemFromCart(String userId, Long productId) {
		// TODO Auto-generated method stub
		Optional<Products> productOpt = prodRepo.findById(productId);
		Optional<User> userOpt = userRepo.findById(Long.valueOf(userId));
		
		if(productOpt.isPresent() && userOpt.isPresent()) {
			cartRepo.deleteByUserAndProduct(userOpt.get(),productOpt.get());
			return true;
		}
		return false;
		/*
		 * if(productOpt.isEmpty()) { return false; }
		 * 
		 * 
		 * 
		 * if(userOpt.isEmpty()) { return false; }
		 * 
		 * 
		 * userOpt.flatMap(user -> productOpt.map(product ->{
		 * cartRepo.deleteByUserAndProduct(user,product); return true; })
		 * 
		 * );
		 */

	}

	public List<CartItem>  getCartItem(String userId) {
		// TODO Auto-generated method stub
		return userRepo.findById(Long.valueOf(userId))
				.map(cartRepo::findByUser)
				.orElseGet(List::of);
	}

	public void clearCart(String userId) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}

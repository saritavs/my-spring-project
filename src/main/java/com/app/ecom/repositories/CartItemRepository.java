package com.app.ecom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecom.models.CartItem;
import com.app.ecom.models.Products;
import com.app.ecom.models.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	CartItem findByUserAndProduct(User user, Products product);
	
	void deleteByUserAndProduct(User user, Products product);
	
	List<CartItem> findByUser(User user);

	void deleteByUser(User user);

}

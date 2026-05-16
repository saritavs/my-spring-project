package com.app.ecom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.ecom.models.Products;

@Repository
public interface ProductsRepositories extends JpaRepository<Products, Long> {
	 List<Products> findByActiveTrue();

	 @Query("""
			    SELECT p 
			    FROM products p
			    WHERE p.active = true
			      AND p.stockQuantity > 0
			      AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyWord, '%'))
			""")
	 //@Query("select p from products where p.active=true and p.stockQuantity > 0 and LOWER(p.name) like  LOWER(CONCAT('%',:keyWord,'%') )")
	 List<Products> searchProducts(@Param("keyWord") String keyWord);
		 
	 

}

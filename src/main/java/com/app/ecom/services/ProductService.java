package com.app.ecom.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;
import com.app.ecom.models.Products;
import com.app.ecom.repositories.ProductsRepositories;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
	
	private final ProductsRepositories productRepo;
	

	public ProductResponse createProduct(ProductRequest productRequest) {
		Products product = new Products();
		updateProductFromRequest(product,productRequest);
		
		Products savedProduct  = productRepo.save(product);
	     
		return mapToProductResponse(savedProduct);
	}
																																																																							

	private ProductResponse mapToProductResponse(Products savedProduct) {
		// TODO Auto-generated method stub
		ProductResponse productResponse = new ProductResponse();
		productResponse.setName(savedProduct.getName());
		productResponse.setDescrption(savedProduct.getDescription());
		productResponse.setImageUrl(savedProduct.getImageUrl());
		productResponse.setStockQuantity(savedProduct.getStockQuantity());
		productResponse.setCategory(savedProduct.getCategory());
		return productResponse;
	}


	private void updateProductFromRequest(Products product, ProductRequest productRequest) {
		// TODO Auto-generated method stub
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setPrice(productRequest.getPrice());
		product.setImageUrl(productRequest.getImageUrl());
		product.setCategory(productRequest.getCategory());
		product.setStockQuantity(productRequest.getStockQuantity());
		
	}


	public Optional<Object> updateProduct(Long id, ProductRequest productRequest) {
		// TODO Auto-generated method stub
		return productRepo.findById(id)
		.map(exisitingproduct ->{ updateProductFromRequest(exisitingproduct, productRequest);
		Products producteupdated = productRepo.save(exisitingproduct);
		return mapToProductResponse(producteupdated);
	});

}


	public List<ProductResponse> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findByActiveTrue().stream()
		.map(this::mapToProductResponse)
		.collect(Collectors.toList());
		
	}

	/**
	 * Setting product status as in active
	 * @param id
	 */

	public boolean deleteProduct(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id)
				.map(product -> {
					product.setActive(false);
					productRepo.save(product);
		        return true;		
				})
				.orElse(false);
				
		
	
		
	}


	public List<ProductResponse>  searchProduct(String keyWord) {
		return productRepo.searchProducts(keyWord).stream()
		.map(this::mapToProductResponse)
		.collect(Collectors.toList());
		
	}
	
}

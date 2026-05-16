package com.app.ecom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;
import com.app.ecom.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {
	
	private final ProductService productService ; 
	
	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
		return new ResponseEntity<ProductResponse>(productService.createProduct(productRequest),HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
		return productService.updateProduct(id, productRequest)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getProducts(){
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deletProduct(@PathVariable Long id){
		boolean deleted = productService.deleteProduct(id);
		return deleted? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/search")
		public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyWord){
		return ResponseEntity.ok(productService.searchProduct(keyWord));
	}

}

package com.beauty.taty_style.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beauty.taty_style.exceptions.ProductNotFoundException;
import com.beauty.taty_style.models.Product;
import com.beauty.taty_style.services.InstitutProductService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class ProductController {
	
	private InstitutProductService   productService;
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		
		List<Product> products = productService.products();
		
		
		return products;
	}
	
	
	@PostMapping("/products")
	public Product createProduct(@RequestBody Product pdt) {
		
		Product savedProduct = productService.createProduct(pdt);
		
		
		return savedProduct;
	}
	
	
	@GetMapping("/products/{pdtId}")
    public Product getProductById(@PathVariable Long pdtId) throws ProductNotFoundException{
		
		Product existingProduct = productService.getProductByPdtId(pdtId);
		
		
		return existingProduct;
	}
	
	
	
	@PutMapping("/products/{pdtId}")
	public Product updateProduct(@PathVariable Long pdtId,@RequestBody Product pdt) {
		
		Product updatedProduct = productService.updateProduct(pdt, pdtId);
		
		return updatedProduct;
		
	}
	
	
	
	@DeleteMapping("/products/{pdtId}")
	void deleteProduct(Long pdtId) {
		
		productService.deleteProduct(pdtId);
	}
}

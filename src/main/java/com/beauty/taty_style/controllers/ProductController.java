package com.beauty.taty_style.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beauty.taty_style.dtos.ProductDto;
import com.beauty.taty_style.dtos.ProductPageDto;
import com.beauty.taty_style.exceptions.ProductNotFoundException;
import com.beauty.taty_style.models.Product;
import com.beauty.taty_style.services.InstitutProductService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class ProductController {
	
private InstitutProductService   productService;
	
	@GetMapping("/products")
	public ProductPageDto getProducts(@RequestParam(name="page",defaultValue = "0") int page,
			                            @RequestParam(name="size",defaultValue="3") int size){
		
		
		return productService.productPages(page, size);
	}
	
	
	@PostMapping("/products")
	public ProductDto createProduct(@RequestBody Product pdt) {
		
		ProductDto pdtDto = productService.createProduct(pdt);
		
		
		return pdtDto;
	}
	
	
	@GetMapping("/products/{pdtId}")
    public ProductDto getProductById(@PathVariable Long pdtId) throws ProductNotFoundException{
		
		ProductDto existingProduct = productService.getProductByPdtId(pdtId);
		
		
		return existingProduct;
	}
	
	
	@PutMapping("/products/{pdtId}")
	public ProductDto updateProduct(@PathVariable Long pdtId,@RequestBody Product pdt) {
		
		ProductDto updatedProductDto = productService.updateProduct(pdt, pdtId);
		
		return updatedProductDto;
		
	}
	
	
	@PostMapping("/products/product/setSalePrice/{pdtId}")
	public ProductDto salePrice(@PathVariable Long pdtId,@RequestBody Product pdt) {
		
		ProductDto productDto = productService.setOutStockPrice(pdt, pdtId);
		
		return productDto;
	}
	
	
	@DeleteMapping("/products/{pdtId}")
	void deleteProduct(Long pdtId) {
		
		productService.deleteProduct(pdtId);
	}
	
	
	@GetMapping("/products/benefit/{pdtId}")
	public ProductDto benefit(@PathVariable Long pdtId,
			                  @RequestParam(name="page",defaultValue = "1") int page,
			                  @RequestParam(name="size",defaultValue = "2") int size) throws ProductNotFoundException{
		
		
		return productService.consultProduct(pdtId,page,size);
	}
	
	
	@GetMapping("/products/totalBenefit")
	public double total() {
		
		return productService.total();
	}
	
	@GetMapping("/products/search")
	public ProductPageDto searchProducts(String designation,
			@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="size",defaultValue = "2") int size) {
		
		return productService.searchProducts(designation,page, size);
	}
}

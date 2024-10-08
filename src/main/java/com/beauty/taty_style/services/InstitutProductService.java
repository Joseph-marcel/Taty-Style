package com.beauty.taty_style.services;

import java.util.List;

import com.beauty.taty_style.dtos.ProductDto;
import com.beauty.taty_style.exceptions.ProductNotFoundException;
import com.beauty.taty_style.models.*;



public interface InstitutProductService {
	
	//PRODUCT
    ProductDto createProduct(Product pdt);
    Product getProductByPdtId(Long pdtId) throws ProductNotFoundException;
    Product getProductByDesignation(String designation);
    ProductDto updateProduct(Product pdt,Long pdtId);
    ProductDto setOutStockPrice(Product pdt,Long pdtId);
    ProductDto saveProduct(Product pdt);
    void    deleteProduct(Long pdtId);
    List<ProductDto> products();
    double marginAmountPerProduct(Long pdtId);
    ProductDto consultProduct(Long pdtId);
    double total();

}

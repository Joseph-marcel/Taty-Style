package com.beauty.taty_style.services;

import java.util.List;
import com.beauty.taty_style.dtos.ProductDto;
import com.beauty.taty_style.dtos.ProductPageDto;
import com.beauty.taty_style.exceptions.ProductNotFoundException;
import com.beauty.taty_style.models.*;



public interface InstitutProductService {
	
	//PRODUCT
    ProductDto createProduct(Product pdt);
    ProductDto getProductByPdtId(Long pdtId) throws ProductNotFoundException;
    ProductDto getProductByDesignation(String designation);
    ProductDto updateProduct(Product pdt,Long pdtId);
    ProductDto setOutStockPrice(Product pdt,Long pdtId);
    ProductDto saveProduct(Product pdt);
    void    deleteProduct(Long pdtId);
    List<ProductDto> products();
    ProductPageDto searchProducts(String designation,int page,int size);
    ProductPageDto productPages(int page,int size);
    double marginAmountPerProduct(Long pdtId);
    ProductDto consultProduct(Long pdtId,int page, int size);
    double total();

}

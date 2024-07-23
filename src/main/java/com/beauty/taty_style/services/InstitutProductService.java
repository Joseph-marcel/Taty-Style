package com.beauty.taty_style.services;

import java.util.List;

import com.beauty.taty_style.exceptions.ProductNotFoundException;
import com.beauty.taty_style.models.*;

public interface InstitutProductService {
	
	//PRODUCT
    Product createProduct(Product pdt);
    Product getProductByPdtId(Long pdtId) throws ProductNotFoundException;
    Product getProductByDesignation(String designation);
    Product updateProduct(Product pdt,Long pdtId);
    Product saveProduct(Product pdt);
    void    deleteProduct(Long pdtId);
    List<Product> products();

}

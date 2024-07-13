package com.beauty.taty_style.services;

import java.util.List;

import com.beauty.taty_style.exceptions.ProductNotFoundException;
import com.beauty.taty_style.exceptions.StockNotFoundException;
import com.beauty.taty_style.exceptions.StockOperationNotFoundException;
import com.beauty.taty_style.models.Product;
import com.beauty.taty_style.models.Stock;
import com.beauty.taty_style.models.StockOperation;


public interface InstitutService {
	
	//STOCK
	Stock createStock(Stock stock);
	Stock getStockByReference(String ref) throws StockNotFoundException;
	Stock updateStock(String ref,Stock stock) ;
    Stock consultStock(String ref) throws StockNotFoundException;
    Stock  addProductToStock(String code,StockOperation stockOpt);
    Stock  removeProductToStock(String ref,StockOperation stockOpt);
    List<Stock> stocks();
    
    
    
    //STOCKOPERATION
    void creditStockOperation(StockOperation stockOpt,String stockRef,String code);
    void debitStockOperation(StockOperation stockOpt, String stockRef,String code);
    List<StockOperation> listStockOperations(String ref,int page,int size);
    StockOperation getStockOperationByNumber(Long number) throws StockOperationNotFoundException;
    
    
    
    
    //PRODUCT
    Product createProduct(Product pdt);
    Product getProductByCode(String code) throws ProductNotFoundException;
    Product updateProduct(Product pdt,String code);
    void    deleteProduct(String code);
    List<Product> products();
    
    
}

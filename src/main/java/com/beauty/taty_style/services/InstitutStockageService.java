package com.beauty.taty_style.services;

import java.util.List;

import com.beauty.taty_style.exceptions.StockNotFoundException;
import com.beauty.taty_style.models.*;

public interface InstitutStockageService {
	
	//STOCK
		Stock createStock(Stock stock);
		Stock updateStock(String ref,Stock stock) ;
	    Stock consult(String ref) throws StockNotFoundException;
	    void  addProductToStock(String ref,StockOperation stockOpt,Long pdtId);
	    void  removeProductToStock(String ref,StockOperation stockOpt,Long pdtId);
	    List<Stock> stocks();

}

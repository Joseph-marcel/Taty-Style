package com.beauty.taty_style.services;


import com.beauty.taty_style.dtos.StockDto;
import com.beauty.taty_style.dtos.StockDtoPage;
import com.beauty.taty_style.exceptions.StockNotFoundException;
import com.beauty.taty_style.models.*;

public interface InstitutStockageService {
	
	//STOCK
		StockDto createStock(Stock stock);
		StockDto updateStock(String ref,Stock stock) ;
	    StockDto consult(String ref) throws StockNotFoundException;
	    void  addProductToStock(String ref,StockOperation stockOpt,Long pdtId);
	    void  removeProductToStock(String ref,StockOperation stockOpt,Long pdtId);
	    Stock getStockByTitle(String title);
	    StockDtoPage stocks(int page,int size);
	    StockDto history(String ref,int page,int size) throws StockNotFoundException;

}

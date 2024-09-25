package com.beauty.taty_style.services;

import java.util.List;

import com.beauty.taty_style.dtos.StockDto;
import com.beauty.taty_style.exceptions.StockNotFoundException;
import com.beauty.taty_style.models.*;

public interface InstitutStockageService {
	
	//STOCK
		StockDto createStock(Stock stock);
		StockDto updateStock(String ref,Stock stock) ;
	    StockDto consult(String ref) throws StockNotFoundException;
	    void  addProductToStock(String ref,StockOperation stockOpt,Long pdtId);
	    void  removeProductToStock(String ref,StockOperation stockOpt,Long pdtId);
	    StockDto getStockByTitle(String title);
	    List<StockDto> stocks();

}

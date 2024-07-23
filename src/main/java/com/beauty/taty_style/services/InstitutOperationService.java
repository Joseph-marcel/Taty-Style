package com.beauty.taty_style.services;

import java.util.List;

import com.beauty.taty_style.exceptions.StockOperationNotFoundException;
import com.beauty.taty_style.models.*;

public interface InstitutOperationService {
	
	//STOCKOPERATION
    void creditStockOperation(StockOperation stockOpt,String stockRef,Long pdtId);
    void debitStockOperation(StockOperation stockOpt, String stockRef,Long pdtId);
    List<StockOperation> listStockOperations(String ref,int page,int size);
    StockOperation getStockOperationByNumber(Long number) throws StockOperationNotFoundException;
   
}

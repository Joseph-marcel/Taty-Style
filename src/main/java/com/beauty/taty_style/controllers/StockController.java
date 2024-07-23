package com.beauty.taty_style.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beauty.taty_style.models.OperationType;
import com.beauty.taty_style.models.Stock;
import com.beauty.taty_style.models.StockOperation;
import com.beauty.taty_style.services.InstitutOperationService;
import com.beauty.taty_style.services.InstitutStockageService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class StockController {
	
	private InstitutStockageService stockService;
	private InstitutOperationService optService;
	
	
	@GetMapping("/stocks")
	public List<Stock> listStocks(){
		
		return stockService.stocks();
	}
	
	
	@GetMapping("/stocks/{ref}")
	public Stock consultStock(@PathVariable String ref) {
		
		Stock stck = stockService.consult(ref);
		
		return stck;
	}
	
	
	@PostMapping("/stocks")
	public Stock createStock(@RequestBody Stock stck) {
		
		return stockService.createStock(stck);
	}
	
	

	
	@PostMapping("/stocks/{ref}/{pdtId}")
	public void saveOperation(@PathVariable String ref,@PathVariable Long pdtId,@RequestBody StockOperation stockOpt) {
		
		try {
			 if(stockOpt.getType() == OperationType.CREDIT) {
				 optService.creditStockOperation(stockOpt, ref, pdtId);
			 }else {
				 optService.debitStockOperation(stockOpt, ref, pdtId);
			 }
		}catch (Exception e){
			
		}
	}

}

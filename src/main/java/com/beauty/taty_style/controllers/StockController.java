package com.beauty.taty_style.controllers;

import java.util.List;

import javax.naming.InsufficientResourcesException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beauty.taty_style.dtos.StockDto;
import com.beauty.taty_style.dtos.StockHistoryDto;
import com.beauty.taty_style.dtos.StockOperationDto;
import com.beauty.taty_style.exceptions.StockNotFoundException;
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
	public List<StockDto> listStocks(){
		
		return stockService.stocks();
	}
	
	
	@GetMapping("/stocks/{ref}")
	public StockDto consultStock(@PathVariable String ref) {
		
		StockDto stckDto = stockService.consult(ref);
		
		return stckDto;
	}
	
	
	@PostMapping("/stocks")
	public StockDto createStock(@RequestBody Stock stck) {
		
		return stockService.createStock(stck);
	}
	
	

	
	@PostMapping("/stocks/operation/{ref}/{pdtId}")
	public void saveOperation(@PathVariable String ref,@PathVariable Long pdtId,@RequestBody StockOperation stockOpt) throws InsufficientResourcesException{
		
			 if(stockOpt.getType() == OperationType.CREDIT) {
				 optService.creditStockOperation(stockOpt, ref, pdtId);
			 }else {
				 if(stockOpt.getType() == OperationType.DEBIT){ 
					 optService.debitStockOperation(stockOpt, ref, pdtId);
				 }
			 }
	}
	
	
	
	@GetMapping("/stocks/operations")
	public List<StockOperationDto> listStockOperation(){
		
		return optService.listStockOperations();
	}
	
	
	@PostMapping("/stocks/operations/credit/correction/{operationNumber}/{ref}/{pdtId}")
	public void correctCreditStockOperation(@PathVariable Long operationNumber,@PathVariable String ref,@PathVariable Long pdtId) {
		
		optService.updateCreditStockOperation(operationNumber, ref, pdtId);
	}
	
	
	@GetMapping("/stocks/{ref}/pageOperations")
	public StockHistoryDto getStockHistory(@PathVariable String ref,
			                               @RequestParam(name="page",defaultValue = "0") int page,
			                               @RequestParam(name="size",defaultValue = "2") int size) throws StockNotFoundException{
		
		
		return stockService.history(ref, page, size);
	}

}

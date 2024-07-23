package com.beauty.taty_style.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beauty.taty_style.exceptions.StockNotFoundException;
import com.beauty.taty_style.models.*;
import com.beauty.taty_style.repositories.StockRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class InstitutStockageServiceImpl implements InstitutStockageService{
	
	private  StockRepository stockRepo;
    private  InstitutOperationService instOptService;
	
	
	@Override
	public Stock createStock(Stock stock) {
		// TODO Auto-generated method stub
		stock.setReference(UUID.randomUUID().toString());
		stock.setTitle(stock.getTitle());
		stock.setDateExistant(stock.getDateExistant());
		stock.setNiveauStock(0);
		stock.setValueStock(0);
		stock.setStatus(StockStatus.EMPTY);
		Stock savedStock = stockRepo.save(stock);
		
		return savedStock;
	}
	
	
	@Override
	public Stock consult(String ref) {
		// TODO Auto-generated method stub
		Stock existingStock = stockRepo.findById(ref)
				.orElseThrow(()-> new StockNotFoundException("Ce stock n'existe pas"));
		
		return existingStock;
	}
	
	
	@Override
	public Stock updateStock(String ref, Stock stock) {
		// TODO Auto-generated method stub
		Stock existingStock = stockRepo.findById(ref).orElse(stock);
		      existingStock.setReference(stock.getReference());
		      existingStock.setTitle(stock.getTitle());
		      existingStock.setNiveauStock(stock.getNiveauStock());
		      existingStock.setValueStock(stock.getValueStock());
		      existingStock.setDateExistant(stock.getDateExistant());
		      existingStock.setStatus(stock.getStatus());
	    Stock updatedStock = stockRepo.save(existingStock);
	    
		return updatedStock;
	}
	
	
	@Override
	public void addProductToStock(String ref, StockOperation stockOpt,Long pdtId) {
		// TODO Auto-generated method stub
		instOptService.creditStockOperation(stockOpt, ref, pdtId);
	}

	@Override
	public void removeProductToStock(String ref, StockOperation stockOpt,Long pdtId) {
		// TODO Auto-generated method stub
        instOptService.debitStockOperation(stockOpt, ref, pdtId);
	}
	
	@Override
	public List<Stock> stocks() {
		// TODO Auto-generated method stub
		List<Stock> stocks = stockRepo.findAll();
		return stocks;
	}

}

package com.beauty.taty_style.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beauty.taty_style.dtos.BalanceDto;
import com.beauty.taty_style.dtos.StockDto;
import com.beauty.taty_style.dtos.StockHistoryDto;
import com.beauty.taty_style.dtos.StockOperationDto;
import com.beauty.taty_style.exceptions.StockNotFoundException;
import com.beauty.taty_style.mappers.StockMapperImpl;
import com.beauty.taty_style.models.*;
import com.beauty.taty_style.repositories.StockRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class InstitutStockageServiceImpl implements InstitutStockageService{
	
	private  StockRepository stockRepo;
    private  InstitutOperationService instOptService;
    private  StockMapperImpl dtoMapper;
    
	
	
    //Create Stock entity
	@Override
	public StockDto createStock(Stock stock) {
		// TODO Auto-generated method stub
		
		Stock existingStock = getStockByTitle(stock.getTitle());
		    if(existingStock == null) {
		    	stock.setReference(UUID.randomUUID().toString());
				stock.setTitle(stock.getTitle());
				stock.setDateExistant(stock.getDateExistant());
				stock.setNiveauStock(0);
				stock.setValueStockCredit(0);
				stock.setValueStockDebit(0);
				stock.setLastOperationStatus(StockStatus.EMPTY);
				Stock savedStock = stockRepo.save(stock);
				
				return dtoMapper.fromStock(savedStock);
		    }else {
		    	return dtoMapper.fromStock(existingStock);
		    }
			
	}
	
	
	//Consult Stock
	@Override
	public StockDto consult(String ref) {
		// TODO Auto-generated method stub
		Stock existingStock = stockRepo.findById(ref)
				.orElseThrow(()-> new StockNotFoundException("Ce stock n'existe pas"));
		StockDto stockDto = dtoMapper.fromStock(existingStock);
		List<StockOperationDto> stockOperationDtos = existingStock.getStockOperations().stream()
				                                                  .map(stockOpt -> dtoMapper.fromStockOperation(stockOpt))
				                                                  .collect(Collectors.toList());
		         stockDto.setStockOperationDtos(stockOperationDtos);
		List<BalanceDto> balanceDtos = existingStock.getBalances().stream()
				                                    .map(balance -> dtoMapper.fromBalance(balance))
				                                    .collect(Collectors.toList());
		         stockDto.setBalanceDtos(balanceDtos);
		
		return stockDto;
	}
	
	
	//Update Stock
	@Override
	public StockDto updateStock(String ref, Stock stock) {
		// TODO Auto-generated method stub
		Stock existingStock = stockRepo.findById(ref).orElse(stock);
		      existingStock.setReference(stock.getReference());
		      existingStock.setTitle(stock.getTitle());
		      existingStock.setNiveauStock(stock.getNiveauStock());
		      existingStock.setValueStockCredit(stock.getValueStockCredit());
		      existingStock.setValueStockDebit(stock.getValueStockDebit());
		      existingStock.setDateExistant(stock.getDateExistant());
		      existingStock.setLastOperationStatus(stock.getLastOperationStatus());
	    Stock updatedStock = stockRepo.save(existingStock);
	    
		return dtoMapper.fromStock(updatedStock);
	}
	
	
	//Add product to stock
	@Override
	public void addProductToStock(String ref, StockOperation stockOpt,Long pdtId) {
		// TODO Auto-generated method stub
		
		instOptService.creditStockOperation(stockOpt, ref, pdtId);
	}

	
	//Remove product from stock
	@Override
	public void removeProductToStock(String ref, StockOperation stockOpt,Long pdtId) {
		// TODO Auto-generated method stub
		
        instOptService.debitStockOperation(stockOpt, ref, pdtId);
	}
	
	
	//Get all stocks
	@Override
	public List<StockDto> stocks() {
		// TODO Auto-generated method stub
		List<Stock> stocks = stockRepo.findAll();
		List<StockDto> stockDtos = stocks.stream()
				                         .map(stock -> dtoMapper.fromStock(stock))
				                         .collect(Collectors.toList());
		
		return stockDtos;
	}


	//Get stock by title
	@Override
	public Stock getStockByTitle(String title) {
		// TODO Auto-generated method stub
		
		return stockRepo.findByTitle(title);
	}


	@Override
	public StockHistoryDto history(String ref,int page,int size) throws StockNotFoundException{
		// TODO Auto-generated method stub
		Stock stock = stockRepo.findById(ref).orElse(null);
		if(stock == null) throw new StockNotFoundException("Stock inexistant");
		Page<StockOperation> stockOperationPages = new PageImpl<StockOperation>(stock.getStockOperations(),PageRequest.of(page, size),stock.getStockOperations().size());
		StockHistoryDto stockHistoryDto = new StockHistoryDto();
		
		  List<StockOperationDto> stockOperationDtos = stockOperationPages.getContent().stream()
				  .map(stockOperation ->dtoMapper.fromStockOperation(stockOperation)).collect(Collectors.toList());
		  stockHistoryDto.setStockOperationDtos(stockOperationDtos);
		  stockHistoryDto.setTotalPages(stockOperationPages.getTotalPages());
		 
		                stockHistoryDto.setCurrentPage(page);
		                stockHistoryDto.setSize(size);
		                stockHistoryDto.setReference(ref);
		                stockHistoryDto.setDateExistant(stock.getDateExistant());
		                stockHistoryDto.setNiveauStock(stock.getNiveauStock());
		                stockHistoryDto.setValueStock(stock.getValueStockDebit() - stock.getValueStockCredit());
		                stockHistoryDto.setLastOperationStatus(stock.getLastOperationStatus());
		                stockHistoryDto.setTitle(stock.getTitle());
		                
		return stockHistoryDto;
	}
}

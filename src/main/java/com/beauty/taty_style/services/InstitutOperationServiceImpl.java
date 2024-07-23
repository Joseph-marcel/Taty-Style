package com.beauty.taty_style.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beauty.taty_style.exceptions.*;
import com.beauty.taty_style.models.*;
import com.beauty.taty_style.repositories.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class InstitutOperationServiceImpl implements InstitutOperationService{
	
	private StockOperationRepository  stockOptRepo;
	private InstitutProductService  pdtService;
	private StockRepository             stockRepo;
	private ProductRepository           pdtRepo;
	
	
	@Override
	public void creditStockOperation(StockOperation stockOpt, String ref,Long pdtId) {
		// TODO Auto-generated method stub
		
		   Stock stock = stockRepo.getReferenceById(ref);
		   Product pdt = pdtService.getProductByPdtId(pdtId);
		   
		   stockOpt.setStock(stock);
		   stockOpt.setProduct(pdt);
		   stockOpt.setAmount(stockOpt.getQuantity() * pdt.getInStockPrice());
		   stockOpt.setType(OperationType.CREDIT);
		   stockOptRepo.save(stockOpt);
		   
		   stock.setDateExistant(stockOpt.getDateOperation());
		   stock.setNiveauStock(stock.getNiveauStock() + stockOpt.getQuantity());
		   stock.setValueStock(stock.getValueStock() + (stockOpt.getAmount() * pdt.getInStockPrice()));
		   stock.setStatus(StockStatus.CREDIT);
		   stock.getStockOperations().add(stockOpt);
		   stockRepo.save(stock);
		   
		   pdt.setStockOperation(stockOpt);
		   pdtRepo.save(pdt);
		   
	}
	
	
	
	@Override
	public void debitStockOperation(StockOperation stockOpt, String ref,Long pdtId) {
		// TODO Auto-generated method stub
		Stock stock = stockRepo.getReferenceById(ref);
		Product pdt = pdtService.getProductByPdtId(pdtId);
		List<StockOperation>  creditStockOperations = stock.getStockOperations()
				                                           .stream()
				                                           .filter(st -> st.getType() == stockOpt.getType()).toList();
		
		for(StockOperation stckOpt:creditStockOperations) {
			
			if(stckOpt.getProduct() == pdt) {
				if(stock.getNiveauStock() < stockOpt.getQuantity()) throw new InsuffisantQuantityInStock("Quantité insuffisante,veuillez réapprovisionner le stock");	
				stock.setDateExistant(stockOpt.getDateOperation());
				stock.setNiveauStock(stock.getNiveauStock() - stockOpt.getQuantity());
				stock.setValueStock(stock.getValueStock() - (stockOpt.getQuantity() * pdt.getOutStockPrice()));
				stock.setStatus(StockStatus.DEBIT);
				stock.getStockOperations().add(stockOpt);
				stockRepo.save(stock);
				
				stockOpt.setDateOperation(stockOpt.getDateOperation());
				stockOpt.setProduct(pdt);
				stockOpt.setQuantity(stockOpt.getQuantity());
				stockOpt.setAmount(stockOpt.getQuantity() * pdt.getOutStockPrice());
				stockOpt.setType(OperationType.DEBIT);
				stockOpt.setStock(stock);
				stockOptRepo.save(stockOpt);
				
				pdt.setStockOperation(stockOpt);
				pdt.setOutStockPrice(pdt.getOutStockPrice());
				pdt.setRecordDate(stockOpt.getDateOperation());
				pdt.setMargin(pdt.getOutStockPrice() - pdt.getInStockPrice());
				if(stock.getNiveauStock() < 1) {
					pdt.setStatus(ProductStatus.INSDISPONIBLE);
				}
				pdtRepo.save(pdt);
				
			}
			
		}
	}
	
	
	
	@Override
	public List<StockOperation> listStockOperations(String ref, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public StockOperation getStockOperationByNumber(Long number) {
		// TODO Auto-generated method stub
		StockOperation stockOpt = stockOptRepo.findById(number)
				.orElseThrow(()-> new StockOperationNotFoundException("Cette operation n'existe pas."));
		
		return stockOpt;
	}

}

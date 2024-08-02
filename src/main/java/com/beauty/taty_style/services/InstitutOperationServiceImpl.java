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
	private InstitutProductService    pdtService;
	private StockRepository           stockRepo;
	private ProductRepository         pdtRepo;
	private MarginRepository          marginRepo;
	
	
	
	@Override
	public void creditStockOperation(StockOperation stockOpt, String ref,Long pdtId) {
		// TODO Auto-generated method stub
		
		   Stock stock = stockRepo.getReferenceById(ref);
		   Product pdt = pdtService.getProductByPdtId(pdtId);
		   
		      stockOpt.setStock(stock);
		      stockOpt.setProduct(pdt);
		      stockOpt.setDateOperation(stockOpt.getDateOperation());
		      stockOpt.setQuantity(stockOpt.getQuantity());
		      stockOpt.setAmount(stockOpt.getQuantity() * pdt.getInStockPrice());
		      stockOpt.setType(OperationType.CREDIT);
		   stockOptRepo.save(stockOpt);
		   
		      stock.setDateExistant(stockOpt.getDateOperation());
		      stock.setNiveauStock(stock.getNiveauStock() + stockOpt.getQuantity());
		      stock.setValueStockCredit(stock.getValueStockCredit() + stockOpt.getAmount());
		      stock.setValueStockDebit(0);
		      stock.setLastOperationStatus(StockStatus.CREDIT);
		      stock.getStockOperations().add(stockOpt);
		   stockRepo.save(stock);
		   
		      pdt.getStockOperation().add(stockOpt);
		   pdtRepo.save(pdt);
		   
	}
	
	
	
	@Override
	public void debitStockOperation(StockOperation stockOpt, String ref,Long pdtId) {
		// TODO Auto-generated method stub
		Stock stock = stockRepo.getReferenceById(ref);
		Product pdt = pdtService.getProductByPdtId(pdtId);
		
		List<StockOperation>  creditStockOperations = stock.getStockOperations().stream().filter(st -> st.getType().equals(OperationType.CREDIT)).toList();
		double counter = 1;
			for(StockOperation stckOpt:creditStockOperations) {
				while(counter < 2) {
					if(stckOpt.getProduct() == pdt) {
						if(stock.getNiveauStock() < stockOpt.getQuantity()) throw new InsuffisantQuantityInStock("Il n'y a plus que " + stock.getNiveauStock() + " en stock.");
						   stockOpt.setDateOperation(stockOpt.getDateOperation());
						   stockOpt.setProduct(pdt);
						   stockOpt.setQuantity(stockOpt.getQuantity());
						   stockOpt.setAmount(stockOpt.getQuantity() * pdt.getOutStockPrice());
						   stockOpt.setType(OperationType.DEBIT);
						   stockOpt.setStock(stock);
						stockOptRepo.save(stockOpt);
						 
						stock.setDateExistant(stockOpt.getDateOperation());
						   stock.setNiveauStock(stock.getNiveauStock() - stockOpt.getQuantity());
						   stock.setValueStockDebit(stock.getValueStockDebit() + (stockOpt.getQuantity() * pdt.getOutStockPrice()));
						   stock.setLastOperationStatus(StockStatus.DEBIT);
			               if((stock.getValueStockDebit() - stock.getValueStockCredit()) < 0 ){
			            	   stock.setStockBenefit((stock.getValueStockDebit() - stock.getValueStockCredit()));
			            	   log.info("here");
			               }
						   stock.getStockOperations().add(stockOpt);
						stockRepo.save(stock);
						
						Margin margin = new Margin();
						       margin.setAmount(stockOpt.getQuantity() * (pdt.getOutStockPrice() - pdt.getInStockPrice()));
						       margin.setSaleDate(stockOpt.getDateOperation());
						       margin.setProduct(pdt);
						marginRepo.save(margin);
						
						   pdt.getStockOperation().add(stockOpt);
						   pdt.setRecordDate(stockOpt.getDateOperation());
						   pdt.getMargins().add(margin);
						if(stock.getNiveauStock() < 1) {
							pdt.setStatus(ProductStatus.INSDISPONIBLE);
						}
						pdtRepo.save(pdt);
					}
					counter++;
				}
			}
	}
	
	
	
	@Override
	public List<StockOperation> listStockOperations() {
		// TODO Auto-generated method stub
		
		return stockOptRepo.findAll();
	}
	
	
	@Override
	public StockOperation getStockOperationByNumber(Long operationNumber) {
		// TODO Auto-generated method stub
		StockOperation stockOpt = stockOptRepo.findById(operationNumber)
				.orElseThrow(()-> new StockOperationNotFoundException("Cette operation n'existe pas."));
		
		return stockOpt;
	}



	@Override
	public void updateCreditStockOperation(Long operationNumber, String ref, Long pdtId) {
		// TODO Auto-generated method stub
		
		Stock stock = stockRepo.getReferenceById(ref);
		Product pdt = pdtService.getProductByPdtId(pdtId);
		StockOperation existingStockOpt = getStockOperationByNumber(operationNumber);
		               existingStockOpt.setDateOperation(existingStockOpt.getDateOperation());
		               existingStockOpt.setQuantity(existingStockOpt.getQuantity());
		               existingStockOpt.setType(OperationType.CREDIT);
		               existingStockOpt.setAmount(existingStockOpt.getQuantity() * pdt.getInStockPrice());
		               existingStockOpt.setProduct(pdt);
		               existingStockOpt.setStock(stock);
		               stockOptRepo.save(existingStockOpt);
		               
		               pdt.getStockOperation().add(existingStockOpt);
		               pdtRepo.save(pdt);
		               
		               stock.setDateExistant(existingStockOpt.getDateOperation());
		               stock.setNiveauStock(stock.getNiveauStock());
		               stock.setValueStockDebit(0);
		               stock.setValueStockCredit(stock.getNiveauStock() * pdt.getInStockPrice());
		               stock.setLastOperationStatus(StockStatus.CREDIT);
		               stock.getStockOperations().add(existingStockOpt);
		               stockRepo.save(stock);
		
	}

}

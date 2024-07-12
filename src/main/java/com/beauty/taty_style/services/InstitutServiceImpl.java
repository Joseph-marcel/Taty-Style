package com.beauty.taty_style.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.beauty.taty_style.exceptions.InsuffisantQuantityInStock;
import com.beauty.taty_style.exceptions.ProductNotFoundException;
import com.beauty.taty_style.exceptions.StockNotFoundException;
import com.beauty.taty_style.exceptions.StockOperationNotFoundException;
import com.beauty.taty_style.models.OperationType;
import com.beauty.taty_style.models.Product;
import com.beauty.taty_style.models.Stock;
import com.beauty.taty_style.models.StockOperation;
import com.beauty.taty_style.models.StockStatus;
import com.beauty.taty_style.repositories.ProductRepository;
import com.beauty.taty_style.repositories.StockOperationRepository;
import com.beauty.taty_style.repositories.StockRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class InstitutServiceImpl implements InstitutService{

	
	private ProductRepository pdtRepo;
	private StockRepository  stockRepo;
	private StockOperationRepository  stockOptRepo;
	
	//METHOD STOCKREPOSITORY
	@Override
	public Stock createStock(Stock stock) {
		// TODO Auto-generated method stub
		stock.setReference(stock.getReference());
		stock.setTitle(stock.getTitle());
		stock.setNiveauStock(0);
		stock.setValueStock(0);
		stock.getStatus();
		stock.setStatus(StockStatus.BALANCED);
		stock.setDateExistant(stock.getDateExistant());
		Stock savedStock = stockRepo.save(stock);
		
		return savedStock;
	}

	@Override
	public Stock getStockByReference(String ref) {
		// TODO Auto-generated method stub
		Stock existingStock = stockRepo.findById(ref)
				.orElseThrow(()-> new StockNotFoundException("Ce stock n'existe pas"));
		
		return existingStock;
	}

	@Override
	public Stock updateStock(String ref, Stock stock) {
		// TODO Auto-generated method stub
		Stock existingStock = getStockByReference(ref);
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
	public Stock consultStock(String ref) {
		// TODO Auto-generated method stub
		Stock existingStock = getStockByReference(ref);
		
		return existingStock;
	}

	@Override
	public Stock addProductToStock(String ref, StockOperation stockOpt) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Stock removeProductToStock(String code, StockOperation stockOpt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Stock> stocks() {
		// TODO Auto-generated method stub
		List<Stock> stocks = stockRepo.findAll();
		return stocks;
	}

	
	
	//METHOD STOCKOPERATIONREPOSITORY
	@Override
	public void creditStockOperation(StockOperation stockOpt, String stockRef,String code) {
		// TODO Auto-generated method stub
		   Stock stock = getStockByReference(stockRef);
		   Product pdt = getProductByCode(code);
		   
		   stockOpt.setStock(stock);
		   stockOpt.setProduct(pdt);
		   stockOpt.setType(OperationType.CREDIT);
		   stockOptRepo.save(stockOpt);
		   
		   stock.setDateExistant(stockOpt.getDateOperation());
		   stock.setNiveauStock(stock.getNiveauStock() + stockOpt.getQuantity());
		   stock.setValueStock(stock.getValueStock() + stockOpt.getAmount());
		   stock.setStatus(StockStatus.CREDIT);
		   stock.getStockOperations().add(stockOpt);
		   stockRepo.save(stock);
		   
		   pdt.setStockOperation(stockOpt);
		   pdtRepo.save(pdt);
		   
	}

	@Override
	public void debitStockOperation(StockOperation stockOpt, String stockRef,String code) {
		// TODO Auto-generated method stub
		Stock stock = getStockByReference(stockRef);
		Product pdt = getProductByCode(code);
		
		//Request to search for product in the current stock
		if(stockOpt.getQuantity() > stock.getNiveauStock()) throw new InsuffisantQuantityInStock("Quantité insuffisante en stock");
			
		
	}

	@Override
	public Page<StockOperation> listStockOperations(String code, int page, int size) {
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
	
	
	
	
	//METHOD PRODUCTREPOSITORY
	@Override
	public Product createProduct(Product pdt) {
		// TODO Auto-generated method stub
		Product savedProduct = pdtRepo.save(pdt);
		
		return savedProduct;
	}

	@Override
	public Product getProductByCode(String code) {
		// TODO Auto-generated method stub
		Product existingProduct = pdtRepo.findById(code)
				.orElseThrow(()-> new ProductNotFoundException("Ce produit n'est pas disponible"));
		
		return existingProduct;
	}

	@Override
	public Product updateProduct(Product pdt, String code) {
		// TODO Auto-generated method stub
		Product existingProduct = getProductByCode(code);
		        existingProduct.setDesignation(pdt.getDesignation());
		        existingProduct.setInStockPrice(pdt.getInStockPrice());
		        existingProduct.setOutStockPrice(pdt.getOutStockPrice());
		        existingProduct.setRecordDate(pdt.getRecordDate());
		        existingProduct.setMargin(pdt.getOutStockPrice()-pdt.getOutStockPrice());
		Product updatedProduct = pdtRepo.save(existingProduct);
		
		return updatedProduct;
	}

	@Override
	public void deleteProduct(String code) {
		// TODO Auto-generated method stub
		Product existingProduct = getProductByCode(code);
		        pdtRepo.delete(existingProduct);
	}

	@Override
	public Page<Product> listProducts(int page, int size) {
		// TODO Auto-generated method stub
		Page<Product> products = pdtRepo.listProducts(PageRequest.of(page, size));
		
		return products;
	}

	@Override
	public List<Product> products() {
		// TODO Auto-generated method stub
		List<Product> products = pdtRepo.findAll();
		
		return products;
	}
	
	
}
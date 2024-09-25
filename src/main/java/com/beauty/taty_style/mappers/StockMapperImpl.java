package com.beauty.taty_style.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.beauty.taty_style.dtos.BalanceDto;
import com.beauty.taty_style.dtos.MarginDto;
import com.beauty.taty_style.dtos.ProductDto;
import com.beauty.taty_style.dtos.StockDto;
import com.beauty.taty_style.dtos.StockOperationDto;
import com.beauty.taty_style.models.Balance;
import com.beauty.taty_style.models.Margin;
import com.beauty.taty_style.models.Product;
import com.beauty.taty_style.models.Stock;
import com.beauty.taty_style.models.StockOperation;


@Service
public class StockMapperImpl {

	public MarginDto fromMargin(Margin margin) {
		MarginDto marginDto = new MarginDto();
		BeanUtils.copyProperties(margin, marginDto);
		
		return marginDto;
	}
	
	public Margin fromMarginDto(MarginDto marginDto) {
	   Margin margin = new Margin();
	   BeanUtils.copyProperties(marginDto, margin);
			
			return margin;
	}
	
    public ProductDto fromProduct(Product product) {
	   ProductDto productDto = new ProductDto();
	   BeanUtils.copyProperties(product, productDto);
		
		return productDto;
	}
    
    public Product fromProduct(ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		
		return product;
	}
    
    public StockOperationDto fromStockOperation(StockOperation StockOperation) {
	   StockOperationDto stockOptDto = new StockOperationDto();
	   BeanUtils.copyProperties(StockOperation, stockOptDto);
		
		return stockOptDto;
	}
    
    public StockOperation fromStockOperationDto(StockOperationDto stockOperationDto) {
	   StockOperation stockOpt = new StockOperation();
	   BeanUtils.copyProperties(stockOperationDto, stockOpt);
		
		return stockOpt;
	}
    
    public StockDto fromStock(Stock stock) {
	   StockDto stockDto = new StockDto();
	   BeanUtils.copyProperties(stock, stockDto);
		
		return stockDto;
	}
    
    public Stock fromStockDto(StockDto stockDto) {
	   Stock stock = new Stock();
	   BeanUtils.copyProperties(stockDto, stock);
		
		return stock;
	}
    
    public BalanceDto fromBalance(Balance balance) {
	   BalanceDto balanceDto = new BalanceDto();
	   BeanUtils.copyProperties(balance, balanceDto);
		
		return balanceDto;
	}
    
    public Balance fromBalanceDto(BalanceDto balanceDto) {
	   Balance balance = new Balance();
	   BeanUtils.copyProperties(balanceDto, balance);
		
		return balance;
	}
	
}

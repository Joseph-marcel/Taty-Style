package com.beauty.taty_style.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.beauty.taty_style.dtos.AllowanceDto;
import com.beauty.taty_style.dtos.BalanceDto;
import com.beauty.taty_style.dtos.BillDto;
import com.beauty.taty_style.dtos.CustomerDto;
import com.beauty.taty_style.dtos.MarginDto;
import com.beauty.taty_style.dtos.PackDto;
import com.beauty.taty_style.dtos.ProductDto;
import com.beauty.taty_style.dtos.StockDto;
import com.beauty.taty_style.dtos.StockOperationDto;
import com.beauty.taty_style.models.Allowance;
import com.beauty.taty_style.models.Balance;
import com.beauty.taty_style.models.Bill;
import com.beauty.taty_style.models.Customer;
import com.beauty.taty_style.models.Margin;
import com.beauty.taty_style.models.Pack;
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
    
    public PackDto fromPack(Pack pack) {
    	PackDto packDto = new PackDto();
    	BeanUtils.copyProperties(pack, packDto);
    	
    	return packDto;
    }
    
    
    public Pack fromPackDto(PackDto packDto) {
    	Pack pack = new Pack();
    	BeanUtils.copyProperties(packDto, pack);
    	
    	return pack;
    }
    
    
    public CustomerDto fromCustomer(Customer customer) {
    	CustomerDto customerDto = new CustomerDto();
    	BeanUtils.copyProperties(customer, customerDto);
    	
    	return customerDto;
    }
    
    
    public Customer fromCustomerDto(CustomerDto customerDto) {
    	Customer customer = new Customer();
    	BeanUtils.copyProperties(customerDto, customer);
    	
    	return customer;
    }
    
    
    public BillDto fromBill(Bill bill) {
    	BillDto billDto = new BillDto();
    	BeanUtils.copyProperties(bill, billDto);
    	
    	return billDto;
    }
    
    
    public Bill fromBillDto(BillDto billDto) {
    	Bill bill = new Bill();
    	BeanUtils.copyProperties(billDto, bill);
    	
    	return bill;
    }
    
    
    public AllowanceDto fromAllowance(Allowance allowance) {
    	AllowanceDto allowanceDto = new AllowanceDto();
    	BeanUtils.copyProperties(allowance, allowanceDto);
    	
    	return  allowanceDto;
    }
    
    
    public Allowance fromAllowanceDto(AllowanceDto allowanceDto) {
    	Allowance allowance = new Allowance();
    	BeanUtils.copyProperties(allowance, allowanceDto);
    	
    	return  allowance;
    }
	
}

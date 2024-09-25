package com.beauty.taty_style.services;

import java.util.List;

import com.beauty.taty_style.exceptions.ProductNotFoundException;
import com.beauty.taty_style.exceptions.StockNotFoundException;
import com.beauty.taty_style.exceptions.StockOperationNotFoundException;
import com.beauty.taty_style.models.Bill;
import com.beauty.taty_style.models.Customer;
import com.beauty.taty_style.models.Product;
import com.beauty.taty_style.models.Stock;
import com.beauty.taty_style.models.StockOperation;



public interface InstitutService {
	
	//STOCK
	Stock createStock(Stock stock);
	Stock getStockByReference(String ref) throws StockNotFoundException;
	Stock updateStock(String ref,Stock stock) ;
    Stock consultStock(String ref) throws StockNotFoundException;
    Stock  addProductToStock(String code,StockOperation stockOpt);
    Stock  removeProductToStock(String ref,StockOperation stockOpt);
    List<Stock> stocks();
    
    
    
    //STOCKOPERATION
    void creditStockOperation(StockOperation stockOpt,String stockRef,Long pdtId);
    void debitStockOperation(StockOperation stockOpt, String stockRef,Long pdtId);
    List<StockOperation> listStockOperations(String ref,int page,int size);
    StockOperation getStockOperationByNumber(Long number) throws StockOperationNotFoundException;
    
    
    
    //PRODUCT
    Product createProduct(Product pdt);
    Product getProductByPdtId(Long pdtId) throws ProductNotFoundException;
    Product updateProduct(Product pdt,Long pdtId);
    void    deleteProduct(Long pdtId);
    List<Product> products();
    
    
    
    //CUSTOMER
    Customer createCustomer(Customer cstm);
    Customer getCustomerByCustomerId(Long customerId);
    Customer updateCustomer(Customer cstm,Long customerId);
    
    
    //BILL
    Bill createBill(Bill bill);
    Bill getBillByBillId(String billId);
    Bill updateBill(Bill bill,String billId);
    List<Bill> listBills();
}

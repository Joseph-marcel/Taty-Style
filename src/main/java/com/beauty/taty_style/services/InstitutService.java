package com.beauty.taty_style.services;

import java.util.List;

import com.beauty.taty_style.exceptions.ProductNotFoundException;
import com.beauty.taty_style.exceptions.StockNotFoundException;
import com.beauty.taty_style.exceptions.StockOperationNotFoundException;
import com.beauty.taty_style.models.Allowance;
import com.beauty.taty_style.models.Bill;
import com.beauty.taty_style.models.Brushing;
import com.beauty.taty_style.models.Closure;
import com.beauty.taty_style.models.Customer;
import com.beauty.taty_style.models.DreadLocks;
import com.beauty.taty_style.models.Dyeing;
import com.beauty.taty_style.models.GraftInstall;
import com.beauty.taty_style.models.HairBun;
import com.beauty.taty_style.models.HairRemoval;
import com.beauty.taty_style.models.LayingWicks;
import com.beauty.taty_style.models.MakeUp;
import com.beauty.taty_style.models.Manicure;
import com.beauty.taty_style.models.Pedicure;
import com.beauty.taty_style.models.Product;
import com.beauty.taty_style.models.Rastas;
import com.beauty.taty_style.models.Scrub;
import com.beauty.taty_style.models.Shampoo;
import com.beauty.taty_style.models.Stock;
import com.beauty.taty_style.models.StockOperation;
import com.beauty.taty_style.models.Straightening;
import com.beauty.taty_style.models.WeddingHairCut;


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
    
    
    
    //ALLOWANCE CREATION
    Allowance createBrushing(Brushing brs);
    Allowance createClosure(Closure cls);
    Allowance createDreadLocks(DreadLocks dread);
    Allowance createDyeing(Dyeing dyieng);
    Allowance createGraftInstall(GraftInstall grft);
    Allowance createHairBun(HairBun hrb);
    Allowance createHairRemoval(HairRemoval hrl);
    Allowance createLayingWicks(LayingWicks layW);
    Allowance createMakeUp(MakeUp mkup);
    Allowance createManicure(Manicure man);
    Allowance createPedicure(Pedicure pdc);
    Allowance createRastas(Rastas rst);
    Allowance createScrub(Scrub scrb);
    Allowance createShampoo(Shampoo shp);
    Allowance createStraightening(Straightening straight);
    Allowance createWedding(WeddingHairCut wedding);
    
    
    //ALLOWANCE BY ID
    Allowance getByBrushing(Long number);
    Allowance getByClosure(Long number);
    Allowance getByDreadLocks(Long number);
    Allowance getByDyeing(Long number);
    Allowance getByGraftInstall(Long number);
    Allowance getByHairBun(Long number);
    Allowance getByHairRemoval(Long number);
    Allowance getByLayingWicks(Long number);
    Allowance getByMakeUp(Long number);
    Allowance getByManicure(Long number);
    Allowance getByPedicure(Long number);
    Allowance getByRastas(Long number);
    Allowance getByScrub(Long number);
    Allowance getByShampoo(Long number);
    Allowance getByStraightening(Long number);
    Allowance getByWedding(Long number);
    
    
    //UPDATE ALLOWANCE
    Allowance updateBrushing(Brushing brs,Long number);
    Allowance updateClosure(Closure cls,Long number);
    Allowance updateDreadLocks(DreadLocks dread,Long number);
    Allowance updateDyeing(Dyeing dyieng,Long number);
    Allowance updateGraftInstall(GraftInstall grft,Long number);
    Allowance updateHairBun(HairBun hrb,Long number);
    Allowance updateHairRemoval(HairRemoval hrl,Long number);
    Allowance updateLayingWicks(LayingWicks layW,Long number);
    Allowance updateMakeUp(MakeUp mkUp,Long number);
    Allowance updateManicure(Manicure man,Long number);
    Allowance updatePedicure(Pedicure pdc,Long number);
    Allowance updateRastas(Rastas rst,Long number);
    Allowance updateScrub(Scrub scrb,Long number);
    Allowance updateShampoo(Shampoo shp,Long number);
    Allowance updateStraightening(Straightening straight,Long number);
    Allowance updateWedding(WeddingHairCut wedding,Long number);
    Allowance getAllowanceByNumber(Long number);
    
    
    
    
    //BILL
    Bill createBill(Bill bill);
    Bill getBillByBillId(String billId);
    Bill updateBill(Bill bill,String billId);
    List<Bill> listBills();
}

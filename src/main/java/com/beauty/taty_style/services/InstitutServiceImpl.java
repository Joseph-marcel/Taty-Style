package com.beauty.taty_style.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.beauty.taty_style.exceptions.InsuffisantQuantityInStock;
import com.beauty.taty_style.exceptions.ProductNotFoundException;
import com.beauty.taty_style.exceptions.StockNotFoundException;
import com.beauty.taty_style.exceptions.StockOperationNotFoundException;
import com.beauty.taty_style.models.Bill;
import com.beauty.taty_style.models.Customer;
import com.beauty.taty_style.models.Director;
import com.beauty.taty_style.models.OperationType;
import com.beauty.taty_style.models.Product;
import com.beauty.taty_style.models.ProductStatus;
import com.beauty.taty_style.models.Stock;
import com.beauty.taty_style.models.StockOperation;
import com.beauty.taty_style.models.StockStatus;

import com.beauty.taty_style.repositories.BillRepository;
import com.beauty.taty_style.repositories.CustomerRepository;
import com.beauty.taty_style.repositories.ProductRepository;
import com.beauty.taty_style.repositories.StockOperationRepository;
import com.beauty.taty_style.repositories.StockRepository;

import lombok.AllArgsConstructor;



@Service
@Transactional
@AllArgsConstructor
public class InstitutServiceImpl implements InstitutService{

	
	private ProductRepository pdtRepo;
	private StockRepository  stockRepo;
	private StockOperationRepository  stockOptRepo;
	private CustomerRepository    cstmRepo;
	private BillRepository       billRepo;
	
	
	
	//METHOD STOCKREPOSITORY
	@Override
	public Stock createStock(Stock stock) {
		// TODO Auto-generated method stub
		stock.setReference(stock.getReference());
		stock.setTitle(stock.getTitle());
		stock.setNiveauStock(0);
		stock.setValueStockCredit(0);
		stock.setValueStockDebit(0);
		stock.setLastOperationStatus(StockStatus.EMPTY);
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
		      existingStock.setValueStockCredit(stock.getValueStockCredit());
		      existingStock.setValueStockDebit(stock.getValueStockDebit());
		      existingStock.setDateExistant(stock.getDateExistant());
		      existingStock.setLastOperationStatus(stock.getLastOperationStatus());
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
	public void creditStockOperation(StockOperation stockOpt, String stockRef,Long pdtId) {
		// TODO Auto-generated method stub
		   Stock stock = getStockByReference(stockRef);
		   Product pdt = getProductByPdtId(pdtId);
		   
		   stockOpt.setStock(stock);
		   stockOpt.setProduct(pdt);
		   stockOpt.setAmount(stockOpt.getQuantity() * pdt.getInStockPrice());
		   stockOpt.setType(OperationType.CREDIT);
		   stockOptRepo.save(stockOpt);
		   
		   stock.setDateExistant(stockOpt.getDateOperation());
		   stock.setNiveauStock(stock.getNiveauStock() + stockOpt.getQuantity());
		   stock.setValueStockCredit(stock.getValueStockCredit() + (stockOpt.getAmount() * pdt.getInStockPrice()));
		   stock.setValueStockDebit(0);
		   stock.setLastOperationStatus(StockStatus.CREDIT);
		   stock.getStockOperations().add(stockOpt);
		   stockRepo.save(stock);
		   
		   pdt.getStockOperation().add(stockOpt);
		   pdtRepo.save(pdt);
		   
	}


	@Override
	public void debitStockOperation(StockOperation stockOpt, String stockRef,Long pdtId) {
		// TODO Auto-generated method stub
		Stock stock = getStockByReference(stockRef);
		Product pdt = getProductByPdtId(pdtId);
		List<StockOperation>  creditStockOperations = stock.getStockOperations()
				                                           .stream()
				                                           .filter(st -> st.getType() == stockOpt.getType()).toList();
		
		for(StockOperation stckOpt:creditStockOperations) {
			
			if(stckOpt.getProduct() == pdt) {
				if(stock.getNiveauStock() < stockOpt.getQuantity()) throw new InsuffisantQuantityInStock("Quantité insuffisante,veuillez réapprovisionner le stock");	
				stock.setDateExistant(stockOpt.getDateOperation());
				stock.setNiveauStock(stock.getNiveauStock() - stockOpt.getQuantity());
				stock.setValueStockDebit(0);
				stock.setLastOperationStatus(StockStatus.DEBIT);
				stock.getStockOperations().add(stockOpt);
				stockRepo.save(stock);
				
				stockOpt.setDateOperation(stockOpt.getDateOperation());
				stockOpt.setProduct(pdt);
				stockOpt.setQuantity(stockOpt.getQuantity());
				stockOpt.setAmount(stockOpt.getQuantity() * pdt.getOutStockPrice());
				stockOpt.setType(OperationType.DEBIT);
				stockOpt.setStock(stock);
				stockOptRepo.save(stockOpt);
				
				pdt.getStockOperation().remove(stockOpt);
				pdt.setOutStockPrice(pdt.getOutStockPrice());
				pdt.setRecordDate(stockOpt.getDateOperation());
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
	
	
	
	//METHOD PRODUCTREPOSITORY
	@Override
	public Product createProduct(Product pdt) {
		// TODO Auto-generated method stub
		        pdt.setStatus(ProductStatus.DISPONIBLE);
		        pdt.setOutStockPrice(0);
		Product savedProduct = pdtRepo.save(pdt);
		
		return savedProduct;
	}

	@Override
	public Product getProductByPdtId(Long pdtId) {
		// TODO Auto-generated method stub
		Product existingProduct = pdtRepo.findById(pdtId)
				.orElseThrow(()-> new ProductNotFoundException("Ce produit n'est pas disponible"));
		
		return existingProduct;
	}

	@Override
	public Product updateProduct(Product pdt, Long pdtId) {
		// TODO Auto-generated method stub
		Product existingProduct = getProductByPdtId(pdtId);
		        existingProduct.setDesignation(pdt.getDesignation());
		        existingProduct.setInStockPrice(pdt.getInStockPrice());
		        existingProduct.setOutStockPrice(pdt.getOutStockPrice());
		        existingProduct.setRecordDate(pdt.getRecordDate());
		Product updatedProduct = pdtRepo.save(existingProduct);
		
		return updatedProduct;
	}

	@Override
	public void deleteProduct(Long pdtId) {
		// TODO Auto-generated method stub
		Product existingProduct = getProductByPdtId(pdtId);
		        pdtRepo.delete(existingProduct);
	}


	@Override
	public List<Product> products() {
		// TODO Auto-generated method stub
		List<Product> products = pdtRepo.findAll();
		
		return products;
	}

	
	//METHOD CUSTOMERREPOSITORY
	@Override
	public Customer createCustomer(Customer cstm) {
		// TODO Auto-generated method stub
		Customer cust = Director.customerBuilder()
				                .firstName(cstm.getFirstName())
				                .phoneNumber(cstm.getPhoneNumber())
				                .build();
		Customer savedCstm = cstmRepo.save(cust);
		
		return savedCstm;
	}

	
	@Override
	public Customer getCustomerByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		
		return cstmRepo.findById(customerId).orElse(null);
	}


	@Override
	public Customer updateCustomer(Customer cstm, Long customerId) {
		// TODO Auto-generated method stub
		Customer existingCstm = getCustomerByCustomerId(customerId);
		         existingCstm.setFirstName(cstm.getFirstName());
		         existingCstm.setPhoneNumber(cstm.getPhoneNumber());
		         
		return cstmRepo.save(existingCstm);
	}
	
	
	
	//METHOD BILL
	@Override
	public Bill createBill(Bill bill) {
		// TODO Auto-generated method stub
		Customer cstm = getCustomerByCustomerId(null);
	
		
		Bill invoice = Director.billBuilder()
				            .billId(UUID.randomUUID().toString())
				            .cost(bill.getCost())
				            .deposit(bill.getDeposit())
				            .refund(bill.getDeposit() - bill.getCost())
				            .billDate(bill.getBillDate())
				            .pack(null)
				            .customer(cstm)
				            .build();
		Bill savedInvoice = billRepo.save(invoice);
		
		return savedInvoice;
	}

	@Override
	public Bill getBillByBillId(String billId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill updateBill(Bill bill, String billId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> listBills() {
		// TODO Auto-generated method stub
		return billRepo.findAll();
	}

}

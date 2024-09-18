package com.beauty.taty_style.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.beauty.taty_style.exceptions.InsuffisantQuantityInStock;
import com.beauty.taty_style.exceptions.ProductNotFoundException;
import com.beauty.taty_style.exceptions.StockNotFoundException;
import com.beauty.taty_style.exceptions.StockOperationNotFoundException;
import com.beauty.taty_style.models.Allowance;
import com.beauty.taty_style.models.Bill;
import com.beauty.taty_style.models.Brushing;
import com.beauty.taty_style.models.Closure;
import com.beauty.taty_style.models.Customer;
import com.beauty.taty_style.models.Director;
import com.beauty.taty_style.models.DreadLocks;
import com.beauty.taty_style.models.Dyeing;
import com.beauty.taty_style.models.GraftInstall;
import com.beauty.taty_style.models.HairBun;
import com.beauty.taty_style.models.HairRemoval;
import com.beauty.taty_style.models.LayingWicks;
import com.beauty.taty_style.models.MakeUp;
import com.beauty.taty_style.models.Manicure;
import com.beauty.taty_style.models.OperationType;
import com.beauty.taty_style.models.Pedicure;
import com.beauty.taty_style.models.Product;
import com.beauty.taty_style.models.ProductStatus;
import com.beauty.taty_style.models.Rastas;
import com.beauty.taty_style.models.Scrub;
import com.beauty.taty_style.models.Shampoo;
import com.beauty.taty_style.models.Stock;
import com.beauty.taty_style.models.StockOperation;
import com.beauty.taty_style.models.StockStatus;
import com.beauty.taty_style.models.Straightening;
import com.beauty.taty_style.models.WeddingHairCut;
import com.beauty.taty_style.repositories.AllowanceRepository;
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
	private AllowanceRepository  allowanceRepo;
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
	
	
	
	//ALLOWANCE CREATION METHOD
	@Override
	public Allowance createBrushing(Brushing brs) {
		// TODO Auto-generated method stub
		
		Allowance brushing = Director.brushingBuilder()
				                     .name(brs.getName())
				                     .build();
		Allowance savedBrushing = allowanceRepo.save(brushing);
		
		return savedBrushing;
	}

	@Override
	public Allowance createClosure(Closure cls) {
		// TODO Auto-generated method stub
		Allowance savedClosure = allowanceRepo.save(cls);
		
		return savedClosure;
	}

	@Override
	public Allowance createDreadLocks(DreadLocks dread) {
		// TODO Auto-generated method stub
		          dread.setSize(dread.getSize());
		Allowance savedDreadLocks  = allowanceRepo.save(dread);
		
		return savedDreadLocks;
	}

	@Override
	public Allowance createDyeing(Dyeing dyieng) {
		// TODO Auto-generated method stub
		Allowance savedDyeing  = allowanceRepo.save(dyieng);
		
		return savedDyeing;
	}

	@Override
	public Allowance createGraftInstall(GraftInstall grft) {
		// TODO Auto-generated method stub
		Allowance savedGraftInstall  = allowanceRepo.save(grft);
		
		return savedGraftInstall;
	}

	@Override
	public Allowance createHairBun(HairBun hrb) {
		// TODO Auto-generated method stub
		Allowance savedHairBun  = allowanceRepo.save(hrb);
		
		return savedHairBun;
	}

	@Override
	public Allowance createHairRemoval(HairRemoval hrl) {
		// TODO Auto-generated method stub
		Allowance savedHairRemoval  = allowanceRepo.save(hrl);
		
		return savedHairRemoval;
	}

	@Override
	public Allowance createLayingWicks(LayingWicks layW) {
		// TODO Auto-generated method stub
		Allowance savedLayingWicks  = allowanceRepo.save(layW);
		
		return savedLayingWicks;
	}

	@Override
	public Allowance createMakeUp(MakeUp mkUp) {
		// TODO Auto-generated method stub
		Allowance savedMakeUp  = allowanceRepo.save(mkUp);
		
		return savedMakeUp;
	}

	@Override
	public Allowance createManicure(Manicure man) {
		// TODO Auto-generated method stub
		Allowance savedManicure  = allowanceRepo.save(man);
		
		return savedManicure;
	}

	@Override
	public Allowance createPedicure(Pedicure pdc) {
		// TODO Auto-generated method stub
		Allowance savedPedicure  = allowanceRepo.save(pdc);
		
		return savedPedicure;
	}

	@Override
	public Allowance createRastas(Rastas rst) {
		// TODO Auto-generated method stub
		Allowance savedRastas  = allowanceRepo.save(rst);
		
		return savedRastas;
	}

	@Override
	public Allowance createScrub(Scrub scrb) {
		// TODO Auto-generated method stub
		Allowance savedScrub  = allowanceRepo.save(scrb);
		
		return savedScrub;
	}

	@Override
	public Allowance createShampoo(Shampoo shp) {
		// TODO Auto-generated method stub
		Allowance savedShampoo  = allowanceRepo.save(shp);
		
		return savedShampoo;
	}

	@Override
	public Allowance createStraightening(Straightening straight) {
		// TODO Auto-generated method stub
		Allowance savedStraightening  = allowanceRepo.save(straight);
		
		return savedStraightening;
	}

	@Override
	public Allowance createWedding(WeddingHairCut wedding) {
		// TODO Auto-generated method stub
		Allowance savedWedding  = allowanceRepo.save(wedding);
		
		return savedWedding;
	}

	
	//GET BY ID ALLOWANCE METHOD
	@Override
	public Allowance getByBrushing(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByClosure(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByDreadLocks(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByDyeing(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByGraftInstall(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByHairBun(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByHairRemoval(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByLayingWicks(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByMakeUp(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByManicure(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByPedicure(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByRastas(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByScrub(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByShampoo(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByStraightening(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public Allowance getByWedding(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	
	
	//UPDATE ALLOWANCE METHOD
	@Override
	public Allowance updateBrushing(Brushing brs,Long number) {
		// TODO Auto-generated method stub
		Allowance existingBrushing = getByBrushing(number);
		          existingBrushing.setName(brs.getName());
		Allowance updatedBrushing = allowanceRepo.save(existingBrushing);
		
		return updatedBrushing;
	}

	@Override
	public Allowance updateClosure(Closure cls,Long number) {
		// TODO Auto-generated method stub
		Allowance existingClosure = getByBrushing(number);
                  existingClosure.setName(cls.getName());
        Allowance updatedClosure = allowanceRepo.save(existingClosure);
        
		return updatedClosure;
	}

	@Override
	public Allowance updateDreadLocks(DreadLocks dread,Long number) {
		// TODO Auto-generated method stub
		Allowance existingDreadLocks = getByDreadLocks(number);
                  existingDreadLocks.setName(dread.getName());
                  
        Allowance updatedDreadLocks = allowanceRepo.save(existingDreadLocks);
		
		return updatedDreadLocks;
	}

	@Override
	public Allowance updateDyeing(Dyeing dyeing,Long number) {
		// TODO Auto-generated method stub
		Allowance existingDyeing = getByDyeing(number);
		          existingDyeing.setName(dyeing.getName());
		          
		Allowance updatedDyeing = allowanceRepo.save(existingDyeing);
		
		return updatedDyeing;
	}

	@Override
	public Allowance updateGraftInstall(GraftInstall grft,Long number) {
		// TODO Auto-generated method stub
		Allowance existingGraftInstall = getByGraftInstall(number);
		          existingGraftInstall.setName(grft.getName());
		Allowance updatedGraftInstall = allowanceRepo.save(existingGraftInstall);
		
		return updatedGraftInstall;
	}

	@Override
	public Allowance updateHairBun(HairBun hrb,Long number) {
		// TODO Auto-generated method stub
		Allowance existingHairBun = getByHairBun(number);
		          existingHairBun.setName(hrb.getName());
		Allowance updatedHairBrun = allowanceRepo.save(existingHairBun);
		
		return updatedHairBrun;
	}

	@Override
	public Allowance updateHairRemoval(HairRemoval hrl,Long number) {
		// TODO Auto-generated method stub
		Allowance existingHairRemoval = getByHairRemoval(number);
		          existingHairRemoval.setName(hrl.getName());
		Allowance updatedHairRemoval = allowanceRepo.save(existingHairRemoval);
		
		return updatedHairRemoval;
	}

	@Override
	public Allowance updateLayingWicks(LayingWicks layW,Long number) {
		// TODO Auto-generated method stub
		Allowance existingLayingWicks = getByLayingWicks(number);
		          existingLayingWicks.setName(layW.getName());
		Allowance updatedLAyingWicks = allowanceRepo.save(existingLayingWicks);
		
		return updatedLAyingWicks;
	}

	@Override
	public Allowance updateMakeUp(MakeUp mkUp,Long number) {
		// TODO Auto-generated method stub
		Allowance  existingMakeUp = getByMakeUp(number);
		           existingMakeUp.setName(mkUp.getName());
		Allowance  updatedMakeUp = allowanceRepo.save(existingMakeUp);
		
		return updatedMakeUp;
	}

	@Override
	public Allowance updateManicure(Manicure man,Long number) {
		// TODO Auto-generated method stub
		Allowance existingManicure = getByManicure(number);
		          existingManicure.setName(man.getName());
		Allowance updatedManicure = allowanceRepo.save(existingManicure);
		
		return updatedManicure;
	}

	@Override
	public Allowance updatePedicure(Pedicure pdc,Long number) {
		// TODO Auto-generated method stub
		Allowance existingPedicure = getByPedicure(number);
		          existingPedicure.setName(pdc.getName());
		Allowance updatedPedicure = allowanceRepo.save(existingPedicure);
		
		return updatedPedicure;
	}

	@Override
	public Allowance updateRastas(Rastas rst,Long number) {
		// TODO Auto-generated method stub
		Allowance existingRastas = getByRastas(number);
		          existingRastas.setName(rst.getName());
		Allowance updatedRastas = allowanceRepo.save(existingRastas);
		
		return updatedRastas;
	}

	@Override
	public Allowance updateScrub(Scrub scrb,Long number) {
		// TODO Auto-generated method stub
		Allowance existingScrub = getByScrub(number);
		          existingScrub.setName(scrb.getName());
		Allowance updatedScrub = allowanceRepo.save(existingScrub);
		
		return updatedScrub;
	}

	@Override
	public Allowance updateShampoo(Shampoo shp,Long number) {
		// TODO Auto-generated method stub
		Allowance existingShampoo = getByShampoo(number);
		          existingShampoo.setName(shp.getName());
		Allowance updatedShampoo = allowanceRepo.save(existingShampoo);
		
		return updatedShampoo;
	}

	@Override
	public Allowance updateStraightening(Straightening straight,Long number) {
		// TODO Auto-generated method stub
		Allowance existingStraightening = getByStraightening(number);
		          existingStraightening.setName(straight.getName());
		Allowance updatedStraightening = allowanceRepo.save(existingStraightening);
		
		return updatedStraightening;
	}

	@Override
	public Allowance updateWedding(WeddingHairCut wedding,Long number) {
		// TODO Auto-generated method stub
		Allowance existingWedding = getByWedding(number);
		          existingWedding.setName(wedding.getName());
		Allowance updatedWedding = allowanceRepo.save(existingWedding);
		
		return updatedWedding;
	}
	
	@Override
	public Allowance getAllowanceByNumber(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
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
				            .allowances(null)
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

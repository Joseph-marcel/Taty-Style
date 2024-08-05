package com.beauty.taty_style.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beauty.taty_style.models.Product;
import com.beauty.taty_style.models.ProductStatus;
import com.beauty.taty_style.repositories.ProductRepository;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
@Transactional
public class InstitutProductServiceImpl implements InstitutProductService{
	
	private ProductRepository pdtRepo;
	
	
	@Override
	public Product createProduct(Product pdt) {
		// TODO Auto-generated method stub
	  Product existingPdt = getProductByDesignation(pdt.getDesignation());
	  if(existingPdt==null) {
		    
		    pdt.setStatus(ProductStatus.DISPONIBLE);
	        pdt.setOutStockPrice(0);
	     Product savedProduct = pdtRepo.save(pdt);
	    
	       return savedProduct;
	  }else {
		  
		   return existingPdt;
	  }
		 
	}
	
	
	//Get product by product Id
	@Override
	public Product getProductByPdtId(Long pdtId) {
		// TODO Auto-generated method stub
		Product existingProduct = pdtRepo.findById(pdtId).orElse(null);

				
		return existingProduct;
	}
	
	
	//Update product
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

	
	//Delete product
	@Override
	public void deleteProduct(Long pdtId) {
		// TODO Auto-generated method stub
		Product existingProduct = getProductByPdtId(pdtId);
		        pdtRepo.delete(existingProduct);
	}

    //Set margin attribute for each product
	@Override
	public List<Product> products() {
		// TODO Auto-generated method stub
		List<Product> products = pdtRepo.findAll();
		for(Product p : products) {
			double TotalMargin = marginAmountPerProduct(p.getPdtId());
			       p.setTotalBenefit(TotalMargin);
		}
		
		return products;
	}


	//Create product
	@Override
	public Product saveProduct(Product pdt) {
		// TODO Auto-generated method stub
		Product savedPdt;
		if(pdt.getPdtId() == null) {
			 savedPdt = createProduct(pdt);
			
		}else {
			 savedPdt = updateProduct(pdt, pdt.getPdtId());
			
		}
		
		
		return savedPdt;
	}


	//Find product by designation
	@Override
	public Product getProductByDesignation(String designation) {
		// TODO Auto-generated method stub
		Product pdt = pdtRepo.findByDesignation(designation);
		
		return pdt;
	}


	//Set sale's price for product
	@Override
	public Product setOutStockPrice(Product pdt, Long pdtId) {
		// TODO Auto-generated method stub
		Product existingPdt = getProductByPdtId(pdtId);
		        existingPdt.setOutStockPrice(pdt.getOutStockPrice());
		        
		return pdtRepo.save(existingPdt);
	}


	//Sum up all margin for a product
	@Override
	public double marginAmountPerProduct(Long pdtId) {
		// TODO Auto-generated method stub
	    Product pdt = getProductByPdtId(pdtId);
	    double  totalMargin = pdt.getMargins().stream().mapToDouble(mrg -> mrg.getAmount()).sum();
		    
		return totalMargin;
	}


	//Set total benefit in product consult action
	@Override
	public Product consultProduct(Long pdtId) {
		// TODO Auto-generated method stub
		double benefit = marginAmountPerProduct(pdtId);
		Product pdt = getProductByPdtId(pdtId);
		        pdt.setTotalBenefit(benefit);
		        
		return pdt;
	}

   //Sum up margin for list of product
	@Override
	public double total() {
		// TODO Auto-generated method stub
		List<Product> products = products();
		double total = products.stream().mapToDouble(p -> p.getTotalBenefit()).sum();
		
		return total;
	}

}

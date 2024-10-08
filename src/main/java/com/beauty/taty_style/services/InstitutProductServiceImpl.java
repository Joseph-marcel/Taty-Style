package com.beauty.taty_style.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beauty.taty_style.dtos.MarginDto;
import com.beauty.taty_style.dtos.ProductDto;
import com.beauty.taty_style.dtos.StockOperationDto;
import com.beauty.taty_style.mappers.StockMapperImpl;
import com.beauty.taty_style.models.Product;
import com.beauty.taty_style.models.ProductStatus;
import com.beauty.taty_style.repositories.ProductRepository;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
@Transactional
public class InstitutProductServiceImpl implements InstitutProductService{
	
	private ProductRepository pdtRepo;
	private StockMapperImpl dtoMapper;
	
	
	@Override
	public ProductDto createProduct(Product pdt) {
		// TODO Auto-generated method stub
	  ProductDto pdtDto = null;
	  Product existingPdt = getProductByDesignation(pdt.getDesignation());
	  if(existingPdt==null) {
		    
		    pdt.setStatus(ProductStatus.DISPONIBLE);
	        pdt.setOutStockPrice(0);
	        Product savedProduct = pdtRepo.save(pdt);
	        pdtDto = dtoMapper.fromProduct(savedProduct);
	    
	       return pdtDto;
	  }else {
		  pdtDto = dtoMapper.fromProduct(existingPdt);
		   return pdtDto;
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
	public ProductDto updateProduct(Product pdt, Long pdtId) {
		// TODO Auto-generated method stub
		Product existingProduct = getProductByPdtId(pdtId);
		        existingProduct.setDesignation(pdt.getDesignation());
		        existingProduct.setInStockPrice(pdt.getInStockPrice());
		        existingProduct.setOutStockPrice(pdt.getOutStockPrice());
		        existingProduct.setRecordDate(pdt.getRecordDate());
		Product updatedProduct = pdtRepo.save(existingProduct);
		ProductDto pdtDto = dtoMapper.fromProduct(updatedProduct);
		
		return pdtDto;
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
	public List<ProductDto> products() {
		// TODO Auto-generated method stub
		List<Product> products = pdtRepo.findAll();
		for(Product p : products) {
			double TotalMargin = marginAmountPerProduct(p.getPdtId());
			       p.setTotalBenefit(TotalMargin);
		}
		List<ProductDto> productDtos = products.stream()
				                               .map(product -> dtoMapper.fromProduct(product))
				                               .collect(Collectors.toList());
		
		return productDtos;
	}


	//Create product
	@Override
	public ProductDto saveProduct(Product pdt) {
		// TODO Auto-generated method stub
		ProductDto pdtDto;
		if(pdt.getPdtId() == null) {
			pdtDto = createProduct(pdt);
			
		}else {
			pdtDto = updateProduct(pdt, pdt.getPdtId());
			
		}
		
		
		return pdtDto;
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
	public ProductDto setOutStockPrice(Product pdt, Long pdtId) {
		// TODO Auto-generated method stub
		Product existingPdt = getProductByPdtId(pdtId);
		        existingPdt.setOutStockPrice(pdt.getOutStockPrice());
		        
		return dtoMapper.fromProduct(pdtRepo.save(existingPdt));
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
	public ProductDto consultProduct(Long pdtId) {
		// TODO Auto-generated method stub
		double benefit = marginAmountPerProduct(pdtId);
		Product pdt = getProductByPdtId(pdtId);
		        pdt.setTotalBenefit(benefit);
		ProductDto pdtDto = dtoMapper.fromProduct(pdt);
		List<StockOperationDto> stockOperationDtos = pdt.getStockOperation().stream()
				                                        .map(stockOpt -> dtoMapper.fromStockOperation(stockOpt))
				                                        .collect(Collectors.toList());
		pdtDto.setStockOperationDtos(stockOperationDtos);
		List<MarginDto> marginDtos = pdt.getMargins().stream()
				                        .map(margin -> dtoMapper.fromMargin(margin))
				                        .collect(Collectors.toList());
		pdtDto.setMarginsDto(marginDtos);
		
		return pdtDto;
	}

   //Sum up margin for list of product
	@Override
	public double total() {
		// TODO Auto-generated method stub
		List<ProductDto> productDtos = products();
		double total = productDtos.stream().mapToDouble(p -> p.getTotalBenefit()).sum();
		
		return total;
	}

}

package com.beauty.taty_style.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.beauty.taty_style.models.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(value = "SELECT * FROM Product p ORDER BY p.record_date", nativeQuery = true)
    Page<Product> listProducts(PageRequest pageRequest);
	
	Product findByDesignation(String designation);

	
}

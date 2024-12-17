package com.beauty.taty_style.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beauty.taty_style.models.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(value = "SELECT * FROM Product p ORDER BY p.record_date", nativeQuery = true)
    Page<Product> listProducts(PageRequest pageRequest);
	@Query(value = "SELECT * FROM Product p WHERE p.designation= :designation  ORDER BY p.record_date", nativeQuery = true)
	Page<Product> listSearchProducts(@Param("designation") String designation,PageRequest pageRequest);
	
	Product findByDesignation(String designation);

	
}

package com.beauty.taty_style.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.beauty.taty_style.models.Stock;



public interface StockRepository extends JpaRepository<Stock, String>{
	
	Stock findByTitle(String title);
	@Query(value = "SELECT * FROM Stock s", nativeQuery = true)
    Page<Stock> pageStock(PageRequest pageRequest);
}

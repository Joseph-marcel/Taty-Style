package com.beauty.taty_style.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.beauty.taty_style.models.Stock;
import com.beauty.taty_style.models.StockOperation;



public interface StockRepository extends JpaRepository<Stock, String>{
	
	Stock findByTitle(String title);
    List<StockOperation> findByReference(String reference);
}

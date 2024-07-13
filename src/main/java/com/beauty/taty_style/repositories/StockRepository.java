package com.beauty.taty_style.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.beauty.taty_style.models.Stock;



public interface StockRepository extends JpaRepository<Stock, String>{

}

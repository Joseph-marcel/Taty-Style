package com.beauty.taty_style.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.beauty.taty_style.models.Product;


public interface ProductRepository extends JpaRepository<Product, String>{

}

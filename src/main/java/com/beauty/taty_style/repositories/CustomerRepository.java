package com.beauty.taty_style.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.beauty.taty_style.models.Customer;


public interface CustomerRepository extends JpaRepository<Customer, String>{

}

package com.beauty.taty_style.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.beauty.taty_style.models.Bill;


public interface BillRepository extends JpaRepository<Bill, String>{
	
	List<Bill> findAllByOrderByBillDateDesc();
	Page<Bill> findByOrderByBillDateDesc(Pageable pageable);
	Bill findByBillId(String id);
	
}

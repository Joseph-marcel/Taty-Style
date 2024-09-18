package com.beauty.taty_style.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.beauty.taty_style.models.Allowance;


public interface AllowanceRepository extends JpaRepository<Allowance, Long>{

	List<Allowance> findAllByOrderByNameAsc();
	Allowance findByName(String name);
}

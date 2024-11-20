package com.beauty.taty_style.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.beauty.taty_style.models.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long>{

	Page<Balance> findByStockReference(String reference,Pageable pageable);
}

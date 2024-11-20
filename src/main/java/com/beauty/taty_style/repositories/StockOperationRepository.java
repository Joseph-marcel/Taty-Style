package com.beauty.taty_style.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.beauty.taty_style.models.StockOperation;


public interface StockOperationRepository extends JpaRepository<StockOperation, Long>{

	Page<StockOperation> findByProductPdtId(Long pdtId,Pageable pageable);
	Page<StockOperation> findByStockReference(String reference,Pageable pageable);
}

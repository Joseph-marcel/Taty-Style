package com.beauty.taty_style.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beauty.taty_style.models.StockOperation;

public interface StockOperationRepository extends JpaRepository<StockOperation, Long>{

	Page<StockOperation> findByProductPdtId(Long pdtId,Pageable pageable);
	Page<StockOperation> findByStockReference(String reference,Pageable pageable);
	@Query(value = "SELECT o.product_pdt_id FROM Stock_Operation o WHERE o.stock_reference = :reference", nativeQuery = true)
	Long  findProductId(@Param("reference") String reference);
	@Query(value = "SELECT * FROM Stock_Operation o WHERE o.stock_reference = :reference AND o.type = 'CREDIT' ORDER BY o.date_operation DESC LIMIT 1", nativeQuery = true)
	StockOperation recentLastStockOperationCredit(@Param("reference") String reference);
	@Query(value = "SELECT * FROM Stock_Operation o WHERE o.product_pdt_id = :pdtId", nativeQuery = true)
	List<StockOperation> listStockOperation(@Param("pdtId") Long pdtId);
	
}

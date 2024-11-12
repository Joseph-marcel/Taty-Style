package com.beauty.taty_style.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beauty.taty_style.models.Margin;





public interface MarginRepository extends JpaRepository<Margin, Long>{

	
	@Query(value="SELECT * FROM margin WHERE product_pdt_id = :pdtId ORDER BY sale_date DESC", nativeQuery = true)
	Page<Margin> listMargin(@Param("pdtId") Long pdtId,Pageable pageable);
}

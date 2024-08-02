package com.beauty.taty_style.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beauty.taty_style.models.Margin;




public interface MarginRepository extends JpaRepository<Margin, Long>{
	
	
	
	/*
	 * @Query(
	 * value="SELECT * FROM Margin m WHERE m.product_pdt_id = pdtId AND m.sale_date BETWEEN :startDate AND :endDate"
	 * ,nativeQuery = true) List<Margin> marginPerPeriod(@Param("pdtId")Long
	 * pdtId,@Param("startDate")Date startDate, @Param("endDate")Date endDate);
	 * 
	 * 
	 * @Query(value
	 * ="SELECT * FROM Margin m WHERE m.product_pdt_id = pdtId AND m.sale_date BETWEEN :startDate AND :endDate"
	 * , nativeQuery = true) List<Margin> marginPerPeriod(@Param("startDate") Date
	 * startDate, @Param("endDate") Date endDate);
	 */
}

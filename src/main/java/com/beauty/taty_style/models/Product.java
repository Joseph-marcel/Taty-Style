package com.beauty.taty_style.models;


import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pdtId;
	private String designation;
	private double inStockPrice;
	private double outStockPrice;
	@Temporal(TemporalType.DATE)
	private Date   recordDate;
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
	@Transient
	private double totalBenefit;
	@OneToMany(mappedBy = "product")
	private List<StockOperation> stockOperation;
	@OneToMany(mappedBy = "product")
	private List<Margin> margins;
	
}

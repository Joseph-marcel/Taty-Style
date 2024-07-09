package com.beauty.taty_style.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	private String code;
	private String designation;
	private double buyingPrice;
	private double salePrice;
	private double margin;
	@ManyToOne
	private StockOperation stockOperation;
}

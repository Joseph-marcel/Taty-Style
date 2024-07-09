package com.beauty.taty_style.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	
	private String code;
	private String designation;
	private double buyingPrice;
	private double salingPrice;
	private double margin;

}

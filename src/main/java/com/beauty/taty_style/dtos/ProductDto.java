package com.beauty.taty_style.dtos;

import java.util.Date;
import java.util.List;

import com.beauty.taty_style.models.ProductStatus;

import lombok.Data;


@Data
public class ProductDto {
	
	private String code;
	private String designation;
	private double inStockPrice;
	private double outStockPrice;
	private Date   recordDate;
	private ProductStatus status;
	private double totalBenefit;
	private double margin;
	private List<MarginDto> marginsDto;

}

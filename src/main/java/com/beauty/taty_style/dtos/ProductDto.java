package com.beauty.taty_style.dtos;

import java.util.List;

import com.beauty.taty_style.models.ProductStatus;

import lombok.Data;


@Data
public class ProductDto {
	
	private Long pdtId;
	private String designation;
	private double inStockPrice;
	private double outStockPrice;
	private ProductStatus status;
	private double totalBenefit;
	private List<StockOperationDto> stockOperationDtos;
	private List<MarginDto> marginsDto;

}

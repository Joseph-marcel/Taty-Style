package com.beauty.taty_style.dtos;

import java.util.List;

import lombok.Data;


@Data
public class ProductPageDto {
	
	private int page;
	private int size;
	private int totalPages;
	private List<ProductDto> productDtos;

}

package com.beauty.taty_style.dtos;

import java.util.List;


import lombok.Data;

@Data
public class StockDtoPage {

	private int page;
	private int size;
	private int totalPages;
	private List<StockDto> stockDtos;
}

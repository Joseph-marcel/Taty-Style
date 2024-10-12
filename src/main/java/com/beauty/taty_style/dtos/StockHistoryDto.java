package com.beauty.taty_style.dtos;

import java.util.Date;
import java.util.List;

import com.beauty.taty_style.models.StockStatus;

import lombok.Data;

@Data
public class StockHistoryDto {

	private String reference;
	private String title;
	private Date dateExistant;
	private double niveauStock;
	private StockStatus  lastOperationStatus;
	private double  valueStock;
	private int currentPage;
	private int totalPages;
	private int size;
	private List<StockOperationDto> stockOperationDtos;
}

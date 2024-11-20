package com.beauty.taty_style.dtos;

import java.util.Date;
import java.util.List;
import com.beauty.taty_style.models.StockStatus;
import lombok.Data;

@Data
public class StockDto {
	
	private String  reference;
	private String  title;
	private Date    dateExistant;
	private double  niveauStock;
	private StockStatus  lastOperationStatus;
	private double  valueStockCredit;
	private double  valueStockDebit;
	private int currentPage;
	private int size;
	private int totalPages;
	private List<StockOperationDto> stockOperationDtos;
	private List<BalanceDto>  balanceDtos;

}

package com.beauty.taty_style.dtos;

import java.util.Date;
import java.util.List;
import com.beauty.taty_style.models.StockStatus;
import lombok.Data;

@Data
public class StockDto {
	
	
	private String  reference;
	private String  name;
	private Date    dateExistant;
	private double  niveauStock;
	private StockStatus  status;
	private double  valueStock;
	private List<StockOperationDto> stockOperationsDto;

}

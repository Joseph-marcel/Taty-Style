package com.beauty.taty_style.dtos;

import java.util.Date;
import java.util.List;
import com.beauty.taty_style.models.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
	
	
	private String  reference;
	private String  name;
	private Date    dateExistant;
	private double  niveauStock;
	private StockStatus  status;
	private double  valueStock;
	private List<StockOperationDto> stockOperationsDto;

}

package com.beauty.taty_style.dtos;


import java.util.Date;
import com.beauty.taty_style.models.OperationType;
import lombok.Data;

@Data
public class StockOperationDto {

	private Long    operationNumber;
	private Date    dateOperation;
	private double  amount;
	private double  quantity;
	private OperationType type;
	
}

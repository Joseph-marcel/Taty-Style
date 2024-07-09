package com.beauty.taty_style.dtos;


import java.util.Date;
import java.util.List;
import com.beauty.taty_style.models.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockOperationDto {

	private Long    number;
	private Date    dateOperation;
	private double  amount;
	private double  quantity;
	private OperationType type;
	List<ProductDto> productsDto;
	
}

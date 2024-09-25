package com.beauty.taty_style.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class MarginDto {
	
	private Long marginId;
	private double amount;
	private Date   saleDate;
	private double quantity;

}

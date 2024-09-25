package com.beauty.taty_style.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class BalanceDto {
	
	private Long balanceId;
	private double capital;
	private double sale;
	private double result;
	private Date   balanceDate;
}

package com.beauty.taty_style.dtos;


import java.util.Date;
import lombok.Data;

@Data
public class BillDto {
	
	private String billId;
	private double cost;
	private double deposit;
	private double refund;
	private Date   billDate;
	private CustomerDto customerDto;
	private PackDto packDto;

}

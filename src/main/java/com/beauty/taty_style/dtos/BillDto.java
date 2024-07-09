package com.beauty.taty_style.dtos;


import java.util.Date;

import com.beauty.taty_style.models.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor@AllArgsConstructor
public class BillDto {
	
	private String billId;
	private double price;
	private double deposit;
	private double remainder;
	private double fees;
	private BillStatus status;
	private Date   billDate;
	private CustomerDto customerDto;
	private PackDto packDto;

}

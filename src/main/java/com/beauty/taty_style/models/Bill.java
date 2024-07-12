package com.beauty.taty_style.models;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
	
	@Id
	private String billId;
	private double price;
	private double deposit;
	private double remainder;
	@Enumerated(EnumType.STRING)
	private BillStatus status;
	private double fees;
	private Date   billDate;
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private Pack pack;
	
}

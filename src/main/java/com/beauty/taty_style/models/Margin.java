package com.beauty.taty_style.models;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Margin {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long marginId;
	private double amount;
	@Temporal(TemporalType.DATE)
	private Date   saleDate;
	private double quantity;
	@ManyToOne
	private Product product;
	
}

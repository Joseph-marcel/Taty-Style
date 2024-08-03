package com.beauty.taty_style.models;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public  class StockOperation {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long    operationNumber;
	@Temporal(TemporalType.DATE)
	private Date    dateOperation;
	private double  quantity;
	private double  amount;
	@Enumerated(EnumType.STRING)
	private OperationType type;
	@ManyToOne
	@JsonIgnore
	private Stock   stock;
	@ManyToOne
	private Product product;
	
}

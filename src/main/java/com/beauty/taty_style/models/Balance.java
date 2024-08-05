package com.beauty.taty_style.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Balance {
	
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long balanceId;
	private double capital;
	private double sale;
	private double result;
	@Temporal(TemporalType.DATE)
	private Date   balanceDate;
	@ManyToOne
	@JsonIgnore
	private Stock  stock;

}

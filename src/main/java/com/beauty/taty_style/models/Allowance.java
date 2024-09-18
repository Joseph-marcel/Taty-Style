package com.beauty.taty_style.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Allowance {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long number;
	private String name;
	private double price;
	private Size size;
	private FashionStyle fashion;
	private Color color;
	@ManyToOne
	@JsonIgnore
	private Bill bill;
}

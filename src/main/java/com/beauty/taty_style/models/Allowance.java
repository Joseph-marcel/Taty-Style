package com.beauty.taty_style.models;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	@Enumerated(EnumType.STRING)
	private Size size;
	@Enumerated(EnumType.STRING)
	private FashionStyle fashion;
	@Enumerated(EnumType.STRING)
	private Color color;
	@ManyToMany
	private List<Pack> packs = new ArrayList<Pack>();
}

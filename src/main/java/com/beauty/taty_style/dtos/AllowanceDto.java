package com.beauty.taty_style.dtos;

import com.beauty.taty_style.models.Color;
import com.beauty.taty_style.models.FashionStyle;
import com.beauty.taty_style.models.Size;

import lombok.Data;

@Data
public class AllowanceDto {
	
	private Long number;
	private String name;
	private double price;
	private Size size;
	private FashionStyle fashion;
	private Color color;
    
}

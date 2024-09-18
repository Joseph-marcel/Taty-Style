package com.beauty.taty_style.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("BRUS")
public class Brushing extends Allowance{

	public static class BrushingBuilder{
			
			private Brushing brushing = new Brushing();
			
			
			public BrushingBuilder number(Long number) {
				
				brushing.setNumber(number);
				return this;
			}
			
	        public BrushingBuilder name(String name) {
				
				brushing.setName(name);
				return this;
			}
	        
            public BrushingBuilder price(double price) {
				
				brushing.setPrice(price);
				return this;
			}
			
			public Brushing build() {
				
				return this.brushing;
			}
	
		}
}

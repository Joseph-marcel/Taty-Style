package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("WICK")
public class LayingWicks extends Allowance{
	public static class LayingWicksBuilder{
		
		private LayingWicks layingWicks = new LayingWicks();
		
		
		public LayingWicksBuilder number(Long number) {
			
			layingWicks.setNumber(number);
			return this;
		}
		
        public LayingWicksBuilder name(String name) {
			
        	layingWicks.setName(name);
			return this;
		}
        
        public LayingWicksBuilder price(double price) {
			
        	layingWicks.setPrice(price);
			return this;
		}
        
        
		public LayingWicks build() {
			
			return this.layingWicks;
		}

	}
}

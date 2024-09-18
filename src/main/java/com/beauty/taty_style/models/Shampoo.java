package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("SHAM")
public class Shampoo extends Allowance{
public static class ShampooBuilder{
		
		private Shampoo shampoo = new Shampoo();
		
		
		public ShampooBuilder number(Long number) {
			
			shampoo.setNumber(number);
			return this;
		}
		
        public ShampooBuilder name(String name) {
			
        	shampoo.setName(name);
			return this;
		}
        
        public ShampooBuilder price(double price) {
			
        	shampoo.setPrice(price);
			return this;
		}
		
		public Shampoo build() {
			
			return this.shampoo;
		}
	}
}

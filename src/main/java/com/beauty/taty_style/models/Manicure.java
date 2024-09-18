package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HAND")
public class Manicure extends Allowance{
	public static class ManicureBuilder{
		
		private Manicure manicure = new Manicure();
		
		
		public ManicureBuilder number(Long number) {
			
			manicure.setNumber(number);
			return this;
		}
		
        public ManicureBuilder name(String name) {
			
        	manicure.setName(name);
			return this;
		}
        
        public ManicureBuilder price(double price) {
			
        	manicure.setPrice(price);
			return this;
		}
        
        
		public Manicure build() {
			
			return this.manicure;
		}

	}
}

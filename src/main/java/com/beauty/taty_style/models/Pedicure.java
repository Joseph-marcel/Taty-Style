package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FOOT")
public class Pedicure extends Allowance{
	public static class PedicureBuilder{
		
		private Pedicure pedicure = new Pedicure();
		
		
		public PedicureBuilder number(Long number) {
			
			pedicure.setNumber(number);
			return this;
		}
		
        public PedicureBuilder name(String name) {
			
        	pedicure.setName(name);
			return this;
		}
        
        public PedicureBuilder price(double price) {
			
        	pedicure.setPrice(price);
			return this;
		}
        
        
		public Pedicure build() {
			
			return this.pedicure;
		}

	}
}

package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("WEDD")
public class WeddingHairCut extends Allowance{
	public static class WeddingHairCutBuilder{
		
		private WeddingHairCut wedding = new WeddingHairCut();
		
		
		public WeddingHairCutBuilder number(Long number) {
			
			wedding.setNumber(number);
			return this;
		}
		
        public WeddingHairCutBuilder name(String name) {
			
        	wedding.setName(name);
			return this;
		}
        
        public WeddingHairCutBuilder price(double price) {
			
        	wedding.setPrice(price);
			return this;
		}
        
        
		public WeddingHairCut build() {
			
			return this.wedding;
		}

	}
}

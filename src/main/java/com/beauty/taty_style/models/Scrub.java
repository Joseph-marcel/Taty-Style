package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("SCRUB")
public class Scrub extends Allowance{
	public static class ScrubBuilder{
		
		private Scrub scrub = new Scrub();
		
		
		public ScrubBuilder number(Long number) {
			
			scrub.setNumber(number);
			return this;
		}
		
        public ScrubBuilder name(String name) {
			
        	scrub.setName(name);
			return this;
		}
        
        public ScrubBuilder price(double price) {
			
        	scrub.setPrice(price);
			return this;
		}
        
        
		public Scrub build() {
			
			return this.scrub;
		}

	}
}

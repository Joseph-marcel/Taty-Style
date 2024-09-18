package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("STRAI")
public class Straightening extends Allowance{
public static class StraighteningBuilder{
		
		private Straightening straightening = new Straightening();
		
		
		public StraighteningBuilder number(Long number) {
			
			straightening.setNumber(number);
			return this;
		}
		
        public StraighteningBuilder name(String name) {
			
        	straightening.setName(name);
			return this;
		}
        
        public StraighteningBuilder price(double price) {
			
        	straightening.setPrice(price);
			return this;
		}
		
		public Straightening build() {
			
			return this.straightening;
		}
	}
}

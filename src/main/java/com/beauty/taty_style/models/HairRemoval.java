package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("HAIV")
public class HairRemoval extends Allowance{
	public static class HairRemovalBuilder{
		
		private HairRemoval hairRemoval = new HairRemoval();
		
		
		public HairRemovalBuilder number(Long number) {
			
			hairRemoval.setNumber(number);
			return this;
		}
		
        public HairRemovalBuilder name(String name) {
			
        	hairRemoval.setName(name);
			return this;
		}
        
        public HairRemovalBuilder price(double price) {
			
			hairRemoval.setPrice(price);
			return this;
		}
        
        
		public HairRemoval build() {
			
			return this.hairRemoval;
		}

	}
}

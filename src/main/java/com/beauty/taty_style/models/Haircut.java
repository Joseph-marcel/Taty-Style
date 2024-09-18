package com.beauty.taty_style.models;



import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("HAIR")
public class HairCut extends Allowance{
	public static class HairCutBuilder{
		
		private HairCut hairCut = new HairCut();
		
		
		public HairCutBuilder number(Long number) {
			
			hairCut.setNumber(number);
			return this;
		}
		
        public HairCutBuilder name(String name) {
			
        	hairCut.setName(name);
			return this;
		}
        
		
        public HairCutBuilder price(double price) {
			
			hairCut.setPrice(price);
			return this;
		}
		
		public HairCut build() {
			
			return this.hairCut;
		}

	}
}

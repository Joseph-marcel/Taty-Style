package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("HAIR")
public class Haircut extends Allowance{
	public static class HaircutBuilder{
		
		private Haircut hairCut = new Haircut();
		
		
		public HaircutBuilder number(Long number) {
			
			hairCut.setNumber(number);
			return this;
		}
		
        public HaircutBuilder name(String name) {
			
        	hairCut.setName(name);
			return this;
		}
        
        
        public HaircutBuilder pack(Pack pack) {
			
        	hairCut.setPack(pack);
			return this;
		}
		
		
		public Haircut build() {
			
			return this.hairCut;
		}

	}
}

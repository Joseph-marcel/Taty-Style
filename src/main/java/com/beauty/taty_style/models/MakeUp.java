package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FACE")
public class MakeUp extends Allowance{
	public static class MakeUpBuilder{
		
		private MakeUp makeUp = new MakeUp();
		
		
		public MakeUpBuilder number(Long number) {
			
			makeUp.setNumber(number);
			return this;
		}
		
        public MakeUpBuilder name(String name) {
			
        	makeUp.setName(name);
			return this;
		}
        
        
        public MakeUpBuilder pack(Pack pack) {
			
        	makeUp.setPack(pack);
			return this;
		}
		
		
		public MakeUp build() {
			
			return this.makeUp;
		}

	}
}

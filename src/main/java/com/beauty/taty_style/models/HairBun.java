package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("HAIB")
public class HairBun extends Allowance{
	
	public static class HairBunBuilder{
		
		private HairBun hairBun = new HairBun();
		
		
		public HairBunBuilder number(Long number) {
			
			hairBun.setNumber(number);
			return this;
		}
		
        public HairBunBuilder name(String name) {
			
        	hairBun.setName(name);
			return this;
		}
        
        
        public HairBunBuilder pack(Pack pack) {
			
        	hairBun.setPack(pack);
			return this;
		}
		
		
		public HairBun build() {
			
			return this.hairBun;
		}

	}

}

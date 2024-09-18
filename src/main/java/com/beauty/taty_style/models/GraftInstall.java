package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("GRAF")
public class GraftInstall extends Allowance{
	
	public static class GraftInstallBuilder{
		
		private GraftInstall graftInstall = new GraftInstall();
		
		
		public GraftInstallBuilder number(Long number) {
			
			graftInstall.setNumber(number);
			return this;
		}
		
        public GraftInstallBuilder name(String name) {
			
        	graftInstall.setName(name);
			return this;
		}
        
        public GraftInstallBuilder price(double price) {
			
			graftInstall.setPrice(price);
			return this;
		}
		
		public GraftInstall build() {
			
			return this.graftInstall;
		}

	}

}

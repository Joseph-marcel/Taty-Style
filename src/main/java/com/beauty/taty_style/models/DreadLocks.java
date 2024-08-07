package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@DiscriminatorValue("DREAD")
public class DreadLocks extends Allowance{
	
	   @Enumerated(EnumType.STRING)
       private Size size;
	   
	   public static class DreadLocksBuilder{
			
			private DreadLocks dreadL = new DreadLocks();
			
			
			public DreadLocksBuilder number(Long number) {
				
				dreadL.setNumber(number);
				return this;
			}
			
	        public DreadLocksBuilder name(String name) {
				
	        	dreadL.setName(name);
				return this;
			}
	        
	        
	        public DreadLocksBuilder size(Size size) {
				
	        	dreadL.setSize(size);
				return this;
			}
	        
	        
            public DreadLocksBuilder pack(Pack pack) {
				
            	dreadL.setPack(pack);
				return this;
			}
			
			
			public DreadLocks build() {
				
				return this.dreadL;
			}

		}
}

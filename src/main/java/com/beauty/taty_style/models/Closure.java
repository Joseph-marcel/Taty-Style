package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("CLOS")
public class Closure extends Allowance{

	public static class ClosureBuilder{
			
			private Closure closure = new Closure();
			
			
			public ClosureBuilder number(Long number) {
				
				closure.setNumber(number);
				return this;
			}
			
	        public ClosureBuilder name(String name) {
				
	        	closure.setName(name);
				return this;
			}
	        
	        
	        public ClosureBuilder pack(Pack pack) {
				
	        	closure.setPack(pack);
				return this;
			}
			
			
			public Closure build() {
				
				return this.closure;
			}
	
		}
}

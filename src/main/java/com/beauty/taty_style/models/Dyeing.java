package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@DiscriminatorValue("DYEI")
public class Dyeing extends Allowance{
	
	private Color color;
 
	public static class DyeingBuilder{
			
			private Dyeing dyeing = new Dyeing();
			
			
			public DyeingBuilder number(Long number) {
				
				dyeing.setNumber(number);
				return this;
			}
			
	        public DyeingBuilder name(String name) {
				
	        	dyeing.setName(name);
				return this;
			}
	        
	        
            public DyeingBuilder color(Color color) {
				
	        	dyeing.setColor(color);
				return this;
			}
	        
	        
	        public DyeingBuilder pack(Pack pack) {
				
	        	dyeing.setPack(pack);
				return this;
			}
			
			
			public Dyeing build() {
				
				return this.dyeing;
			}
	
		}
}

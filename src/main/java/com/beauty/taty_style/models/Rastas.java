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
@DiscriminatorValue("RAST")
public class Rastas extends Allowance{
	
	private FashionStyle fashion;
	private Size         size;
	public static class RastasBuilder{
		
		private Rastas rastas = new Rastas();
		
		
		public RastasBuilder number(Long number) {
			
			rastas.setNumber(number);
			return this;
		}
		
        public RastasBuilder name(String name) {
			
        	rastas.setName(name);
			return this;
		}
        
        
        public RastasBuilder pack(Pack pack) {
			
        	rastas.setPack(pack);
			return this;
		}
		
		
		public Rastas build() {
			
			return this.rastas;
		}

	}

}

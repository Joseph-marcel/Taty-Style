package com.beauty.taty_style.models;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pack {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long packId;
	@OneToMany(mappedBy = "pack")
	private List<Bill> bills;
	@OneToMany(mappedBy = "pack",fetch = FetchType.EAGER)
	private List<Allowance> allowances;

    public static class PackBuilder{
		
		private Pack pack = new Pack();
		
		
		public PackBuilder packId(Long packId) {
			
			pack.packId = packId;
			return this;
		}
		
		
		public PackBuilder bills() {
			
			pack.bills = new ArrayList<Bill>();
			return this;
		}
		
        public PackBuilder allowances() {
			
			pack.allowances = new ArrayList<Allowance>();
			return this;
		}
		
		public Pack build() {
			
			return this.pack;
		}

	}
}

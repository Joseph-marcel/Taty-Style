package com.beauty.taty_style.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
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
public class Customer {

	@Id 
	private String customerId;
	private String name;
	@OneToMany(mappedBy = "customer")
	private List<Bill> bills;
	
    public static class CustomerBuilder{
		
		private Customer customer = new Customer();
		
		
		public CustomerBuilder customerId(String customerId) {
			
			customer.customerId = customerId;
			return this;
		}
		
		
		public CustomerBuilder bills() {
			
			customer.bills = new ArrayList<Bill>();
			return this;
		}
		
        
		public Customer build() {
			
			return this.customer;
		}
	}
}

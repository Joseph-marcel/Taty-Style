package com.beauty.taty_style.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
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
public class Customer {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String district;
	@OneToMany(mappedBy = "customer")
	private List<Bill> bills;
	
    public static class CustomerBuilder{
		
		private Customer customer = new Customer();
		
		
		public CustomerBuilder customerId(Long customerId) {
			
			customer.customerId = customerId;
			return this;
		}
		
		
        public CustomerBuilder firstName(String firstName) {
			
			customer.firstName = firstName;
			return this;
		}
		
        
        public CustomerBuilder lastName(String lastName) {
			
			customer.lastName = lastName;
			return this;
		}


        public CustomerBuilder phoneNumber(String phoneNumber) {
	
	        customer.phoneNumber = phoneNumber;
	        return this;
        }
        
        
        public CustomerBuilder district(String district) {
        	
	        customer.district = district;
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

package com.beauty.taty_style.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
	
	@Id
	private String billId;
	private double cost;
	private double deposit;
	private double refund;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date   billDate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Default
	private Customer customer = new Customer();
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Default
	private Pack pack = new Pack();
	
	
    public static class BillBuilder{
		
		private Bill bill = new Bill();
		
		public BillBuilder billId(String billId) {
			
			bill.billId = billId;
			return this;
		}
		
		public BillBuilder cost(double cost) {
			
			bill.cost = cost;
			return this;
		}
		
        public BillBuilder deposit(double deposit) {
			
			bill.deposit = deposit;
			return this;
		}
		
        public BillBuilder refund(double refund) {
			
			bill.refund = refund;
			return this;
		}
		
        public BillBuilder  billDate(Date billDate) {
			
			bill.billDate =  billDate;
			return this;
		}
		
        
        public BillBuilder  pack(Pack pack) {
			
			bill.pack =  pack;
			return this;
		}


		public BillBuilder customer(Customer customer) {
		  
			  bill.customer = customer; 
			  return this; 
		  }
		 
		
		public Bill build() {
			
			return this.bill;
		}

	}
	
}

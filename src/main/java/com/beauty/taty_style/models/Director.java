package com.beauty.taty_style.models;

import com.beauty.taty_style.models.Bill.BillBuilder;
import com.beauty.taty_style.models.Customer.CustomerBuilder;


public class Director {
	   
	   
	   public static Customer.CustomerBuilder customerBuilder(){
			
		    return new CustomerBuilder();
	    }
	   
	   public static Bill.BillBuilder billBuilder(){
			
		    return new BillBuilder();
	    }
	   
}

package com.beauty.taty_style.models;

import com.beauty.taty_style.models.Bill.BillBuilder;
import com.beauty.taty_style.models.Customer.CustomerBuilder;
import com.beauty.taty_style.models.Pack.PackBuilder;

public class Director {
	
	   public static Pack.PackBuilder packBuilder(){
		
		    return new PackBuilder();
	    }
	   
	   public static Customer.CustomerBuilder customerBuilder(){
			
		    return new CustomerBuilder();
	    }
	   
	   public static Bill.BillBuilder buillBuilder(){
			
		    return new BillBuilder();
	    }
	   
}

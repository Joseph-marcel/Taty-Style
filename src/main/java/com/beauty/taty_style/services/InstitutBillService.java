package com.beauty.taty_style.services;



import java.util.List;

import com.beauty.taty_style.models.Allowance;
import com.beauty.taty_style.models.Bill;
import com.beauty.taty_style.models.Customer;



public interface InstitutBillService {
	
	Customer getCustomerByPhoneNumber(String phoneNumber);
	Customer createCustomer(Customer cstm);
	
	Allowance createAllowance(Allowance allowance);
	Allowance saveAllowance(Allowance allowance);
	Allowance updateAllowance(Allowance allowance);
	Allowance getAllowanceByName(String name);
	Allowance getAllowanceById(Long number);
	void removeAllowanceById(Long number);
	List<Allowance> getAllowances();
	
	
	Bill createBill(Bill bill);
	Bill getBillByBillId(String billId);
	Bill updateBill(String billId,Bill bill);
	List<Bill> getAllBills();
	double cost();
	
}

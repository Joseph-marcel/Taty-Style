package com.beauty.taty_style.services;



import java.util.List;

import com.beauty.taty_style.dtos.AllowanceDto;
import com.beauty.taty_style.dtos.BillDto;
import com.beauty.taty_style.dtos.BillPageDto;
import com.beauty.taty_style.dtos.CustomerDto;
import com.beauty.taty_style.exceptions.InsuffissantDepositException;
import com.beauty.taty_style.models.Allowance;
import com.beauty.taty_style.models.Bill;
import com.beauty.taty_style.models.Customer;



public interface InstitutBillService {
	
	Customer getCustomerByPhoneNumber(String phoneNumber);
	CustomerDto createCustomer(Customer cstm);
	CustomerDto updateCustomer(Customer cstm,String phoneNumber);
	
	Allowance createAllowance(Allowance allowance);
	AllowanceDto saveAllowance(Allowance allowance);
	Allowance updateAllowance(Allowance allowance);
	Allowance getAllowanceByName(String name);
	Allowance getAllowanceById(Long number);
	void removeAllowanceById(Long number);
	List<AllowanceDto> getAllowances();
	
	
	BillDto createBill(Bill bill) throws InsuffissantDepositException;
	BillDto convertBill(Bill bill);
	BillDto getBillByBillId(String billId);
	BillDto updateBill(String billId,Bill bill);
	List<BillDto> getAllBills();
	BillPageDto  getBillsByPage(int page,int size);
	double cost();
	
}

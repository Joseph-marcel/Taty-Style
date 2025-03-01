package com.beauty.taty_style.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beauty.taty_style.dtos.AllowanceDto;
import com.beauty.taty_style.dtos.BillDto;
import com.beauty.taty_style.dtos.BillPageDto;
import com.beauty.taty_style.exceptions.InsuffissantDepositException;
import com.beauty.taty_style.models.Allowance;
import com.beauty.taty_style.models.Bill;
import com.beauty.taty_style.services.InstitutBillService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BillController {

	private InstitutBillService billService;
	
	
	
	@PostMapping("/billing/allowance/create")
	public AllowanceDto createAllowance(@RequestBody Allowance allowance) {
		
		
		return billService.saveAllowance(allowance);
	}
	
	
	@PostMapping("/billing/bill/create")
	public BillDto createBill(@RequestBody Bill bill) throws InsuffissantDepositException{
		
		
		return billService.createBill(bill);
	}
	
	
	@GetMapping("/billing/bill/{id}")
	public BillDto getBill(@PathVariable String id) {
		
		
		return billService.getBillByBillId(id);
	}
	
	
	@PutMapping("billing/bill/{billId}")
	public BillDto updateBill(@PathVariable String billId,@RequestBody Bill bill) {
		
		
		return billService.updateBill(billId, bill);
	}
	
	
	@GetMapping("/billing/allowances")
	public List<AllowanceDto> getAllowances(){
		
		
		return billService.getAllowances();
	}
	
	
	@GetMapping("/billing/bills")
	public List<BillDto> getAllBills(){
		
		
		return billService.getAllBills();
	}
	
	
	@GetMapping("/billing/billPage")
	public BillPageDto getAllBills(
			 @RequestParam(name="page",defaultValue = "0") int page,
			 @RequestParam(name="size",defaultValue = "2") int size){
		
		
		return billService.getBillsByPage(page, size);
	}
	
}

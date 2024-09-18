package com.beauty.taty_style.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beauty.taty_style.models.Allowance;
import com.beauty.taty_style.models.Bill;
import com.beauty.taty_style.services.InstitutBillService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BillController {

	private InstitutBillService billService;
	
	
	
	@PostMapping("/billing/allowance/create")
	public Allowance createAllowance(@RequestBody Allowance allowance) {
		
		
		return billService.saveAllowance(allowance);
	}
	
	
	@PostMapping("/billing/bill/create")
	public Bill createBill(@RequestBody Bill bill) {
		
		
		return billService.createBill(bill);
	}
	
	
	@GetMapping("/billing/bill/{billId}")
	public Bill getBill(@PathVariable String billId) {
		
		
		return billService.getBillByBillId(billId);
	}
	
	
	@PutMapping("billing/bill/{billId}")
	public Bill updateBill(@PathVariable String billId,@RequestBody Bill bill) {
		
		return billService.updateBill(billId, bill);
	}
	
	
	
	
	
}

package com.beauty.taty_style.services;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.beauty.taty_style.exceptions.InsuffissantDepositException;
import com.beauty.taty_style.models.Allowance;
import com.beauty.taty_style.models.Bill;
import com.beauty.taty_style.models.Customer;
import com.beauty.taty_style.models.Director;
import com.beauty.taty_style.models.Pack;
import com.beauty.taty_style.repositories.AllowanceRepository;
import com.beauty.taty_style.repositories.BillRepository;
import com.beauty.taty_style.repositories.CustomerRepository;
import com.beauty.taty_style.repositories.PackRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
@Transactional
public class InstitutBillServiceImpl implements InstitutBillService{
	
	private AllowanceRepository allowanceRepo;
	private CustomerRepository  custRepo;
	private BillRepository      billRepo;
	private PackRepository      packRepo;
	
	
	
	@Override
	public Customer getCustomerByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		
		return custRepo.findByPhoneNumber(phoneNumber);
	}

	@Override
	public Customer createCustomer(Customer cstm) {
		// TODO Auto-generated method stub
		Customer existingCstm = getCustomerByPhoneNumber(cstm.getPhoneNumber());
		if(existingCstm != null) {
			return existingCstm;
		}
		   Customer savedCstm = custRepo.save(cstm);
		   
		return savedCstm;
	}

	
	
	@Override
	public Allowance createAllowance(Allowance allowance) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(allowance);
	}

	@Override
	public Allowance saveAllowance(Allowance allowance) {
		// TODO Auto-generated method stub
		Allowance newAllowance = null;
		if(allowance.getNumber() == null) {
			newAllowance = createAllowance(allowance);
		}else {
			newAllowance = updateAllowance(allowance);
		}
		
		return newAllowance;
	}

	@Override
	public Allowance updateAllowance(Allowance allowance) {
		// TODO Auto-generated method stub
		Allowance savedAllowance = getAllowanceByName(allowance.getName());
		          savedAllowance.setPrice(allowance.getPrice());
		          savedAllowance.setColor(allowance.getColor());
		          savedAllowance.setFashion(allowance.getFashion());
		          savedAllowance.setSize(allowance.getSize());
		          
		return allowanceRepo.save(savedAllowance);
	}
	
	@Override
	public Allowance getAllowanceByName(String name) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findByName(name);
	}

	@Override
	public List<Allowance> getAllowances() {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findAllByOrderByNameAsc();
	}

	@Override
	public Allowance getAllowanceById(Long number) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findById(number).orElse(null);
	}

	@Override
	public void removeAllowanceById(Long number) {
		// TODO Auto-generated method stub
		Allowance existingAllowance = getAllowanceById(number);
		          getAllowances().remove(existingAllowance);
	}
	
	
	

	@Override
	public Bill createBill(Bill bill) {
		// TODO Auto-generated method stub
		Customer cstm = createCustomer(bill.getCustomer());
		Pack pack = createPack(bill.getPack());
		     getAllowances().forEach(all -> pack.getAllowances().add(all));
		     getAllowances().forEach(all -> all.getPacks().add(pack));
		     if((bill.getDeposit() - cost()) < 0) throw new InsuffissantDepositException("Somme inférieur au montant total des prestations");
		
		         bill = Director.billBuilder()
		        		        .billId(UUID.randomUUID().toString())
		        		        .billDate(bill.getBillDate())
		        		        .cost(cost())
		        		        .deposit(bill.getDeposit())
		        		        .refund(bill.getDeposit() - cost())
		        		        .pack(pack)
		        		        .customer(cstm)
		        		        .build();	
		         Bill savedBill = billRepo.save(bill);
		         
		         
		return savedBill;
	}
	
	@Override
	public Bill getBillByBillId(String billId) {
		// TODO Auto-generated method stub
		Bill existingBill = billRepo.findById(billId).orElse(null);
		     
		return existingBill;
	}

	
	@Override
	public Bill updateBill(String billId, Bill bill) {
		// TODO Auto-generated method stub
		Bill existingBill = getBillByBillId(billId);
		     existingBill.setBillDate(bill.getBillDate());
		     existingBill.setDeposit(bill.getDeposit());
		     
		return billRepo.save(existingBill);
	}

	
	@Override
	public List<Bill> getAllBills() {
		// TODO Auto-generated method stub
		
		return billRepo.findAllByOrderByBillDateDesc();
	}

	
	@Override
	public double cost() {
		// TODO Auto-generated method stubs
		     
		return getAllowances().stream().mapToDouble(all -> all.getPrice()).sum();
	}

	
	@Override
	public Pack createPack(Pack pack) {
		// TODO Auto-generated method stub
		
		return packRepo.save(pack);
	}

}

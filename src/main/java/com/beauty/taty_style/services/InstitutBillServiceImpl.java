package com.beauty.taty_style.services;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beauty.taty_style.dtos.AllowanceDto;
import com.beauty.taty_style.dtos.BillDto;
import com.beauty.taty_style.dtos.BillPageDto;
import com.beauty.taty_style.dtos.CustomerDto;
import com.beauty.taty_style.exceptions.InsuffissantDepositException;
import com.beauty.taty_style.exceptions.NotFoundBillException;
import com.beauty.taty_style.mappers.StockMapperImpl;
import com.beauty.taty_style.models.Allowance;
import com.beauty.taty_style.models.Bill;
import com.beauty.taty_style.models.Customer;
import com.beauty.taty_style.models.Director;
import com.beauty.taty_style.repositories.AllowanceRepository;
import com.beauty.taty_style.repositories.BillRepository;
import com.beauty.taty_style.repositories.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class InstitutBillServiceImpl implements InstitutBillService{
	
	private AllowanceRepository allowanceRepo;
	private CustomerRepository  custRepo;
	private BillRepository      billRepo;
	private StockMapperImpl     dtoMapper;
	
	
	
	
	@Override
	public Customer getCustomerByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		
		return custRepo.findByPhoneNumber(phoneNumber);
	}

	
	@Override
	public CustomerDto createCustomer(Customer cstm) {
		// TODO Auto-generated method stub
		Customer existingCstm = getCustomerByPhoneNumber(cstm.getPhoneNumber());
		if(existingCstm != null) {
			return dtoMapper.fromCustomer(existingCstm);
		}
		   CustomerDto savedCstmDto = dtoMapper.fromCustomer(custRepo.save(cstm));
		   
		return savedCstmDto;
	}
	
	
	@Override
	public Allowance createAllowance(Allowance allowance) {
		// TODO Auto-generated method stub
		Allowance savedAllowance = allowanceRepo.save(allowance);
		
		return savedAllowance;
	}

	
	@Override
	public AllowanceDto saveAllowance(Allowance allowance) {
		// TODO Auto-generated method stub
		Allowance newAllowance = null;
		Allowance checkedAllowance = getAllowanceByName(allowance.getName());
		if(checkedAllowance == null) {
			newAllowance = createAllowance(allowance);
		}else {
			newAllowance = updateAllowance(allowance);
		}
		
		    AllowanceDto newAllowanceDto = dtoMapper.fromAllowance(newAllowance);
		    
		return newAllowanceDto;
	}

	
	@Override
	public CustomerDto updateCustomer(Customer cstm, String phoneNumber) {
		// TODO Auto-generated method stub
		Customer existingCstm = getCustomerByPhoneNumber(phoneNumber);
		         existingCstm.setFirstName(cstm.getFirstName());
		         existingCstm.setPhoneNumber(cstm.getPhoneNumber());
		         
		return dtoMapper.fromCustomer(existingCstm);
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
	public List<AllowanceDto> getAllowances() {
		// TODO Auto-generated method stub
		List<Allowance> allowances = allowanceRepo.findAllByOrderByNameAsc();
		List<AllowanceDto> allowancesDto = allowances.stream().map(allowance -> dtoMapper.fromAllowance(allowance)).collect(Collectors.toList());
		
		return allowancesDto;
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
		/*
		 * List<Allowance> allowances = getAllowanceDtos().stream() .map(allowanceDto ->
		 * dtoMapper.fromAllowanceDto(allowanceDto)) .collect(Collectors.toList());
		 * allowances.remove(existingAllowance);
		 */
	}
	
	
	

	@Override
	public BillDto createBill(Bill bill) {
		// TODO Auto-generated method stub
		CustomerDto cstmDto = createCustomer(bill.getCustomer());
		Customer cstm = dtoMapper.fromCustomerDto(cstmDto);
		
		bill.getAllowances().forEach(allowance -> saveAllowance(allowance));
		
	    if((bill.getDeposit() - bill.getCost()) < 0) throw new InsuffissantDepositException("Somme inférieur au montant total des prestations");
	
        bill = Director.billBuilder()
        		       .billId(UUID.randomUUID().toString())
        		       .billDate(bill.getBillDate())
        		       .cost(bill.getCost())
        		       .deposit(bill.getDeposit())
        		       .refund(bill.getDeposit() - bill.getCost())
        		       .allowances(bill.getAllowances())
        		       .customer(cstm)
        		       .build();	
        Bill savedBill = billRepo.save(bill);
        
		         
		return convertBill(savedBill);
	}
	
	
	@Override
	public BillDto getBillByBillId(String id)  throws NotFoundBillException {
		// TODO Auto-generated method stub
		Bill existingBill = billRepo.findByBillId(id);
		
		if(existingBill == null) throw new NotFoundBillException("Facture introuvable");

		
		return convertBill(existingBill);
	}

	
	@Override
	public BillDto updateBill(String billId, Bill bill) {
		// TODO Auto-generated method stub
		BillDto existingBillDto = getBillByBillId(billId);
		Bill existingBill = dtoMapper.fromBillDto(existingBillDto);
		     existingBill.setBillDate(bill.getBillDate());
		     existingBill.setDeposit(bill.getDeposit());
		     
		return dtoMapper.fromBill(billRepo.save(existingBill));
	}

	
	@Override
	public List<BillDto> getAllBills() {
		// TODO Auto-generated method stub
		List<Bill> bills = billRepo.findAllByOrderByBillDateDesc();
		List<BillDto> billDtos = bills.stream()
				                      .map(bill -> dtoMapper.fromBill(bill))
				                      .collect(Collectors.toList());
		              
		
		return billDtos;
	}

	
	@Override
	public double cost() {
		// TODO Auto-generated method stubs
		     
		return getAllowances().stream().mapToDouble(all -> all.getPrice()).sum();
	}

	
	
	

	@Override
	public BillDto convertBill(Bill bill) {
		// TODO Auto-generated method stub
		BillDto billDto = dtoMapper.fromBill(bill);
		Customer cstm = getCustomerByPhoneNumber(bill.getCustomer().getPhoneNumber());
		        billDto.setCustomerDto(dtoMapper.fromCustomer(cstm));
				
				  List<AllowanceDto> allowanceDtos = bill.getAllowances().stream() .map(allowance -> dtoMapper.fromAllowance(allowance)).collect(Collectors.toList()); 
				  
				  billDto.setAllowanceDtos(allowanceDtos);
				 
		        
		return billDto;
	}


	
	  @Override
	  public BillPageDto getBillsByPage(int page,int size) {
	  //Auto-generated method stub 
	  Page<Bill> billPages = billRepo.findByOrderByBillDateDesc(PageRequest.of(page, size));
	  List<BillDto> billDtos = billPages.getContent().stream().map(bill  -> dtoMapper.fromBill(bill)).collect(Collectors.toList());
	  BillPageDto billPageDto = new BillPageDto();
	              billPageDto.setBillDtos(billDtos);
	              billPageDto.setPageSize(size);
	              billPageDto.setCurrentPage(page);
	              billPageDto.setTotalPages(billPages.getTotalPages());
	  
	  
	  return billPageDto; 
	  }




}

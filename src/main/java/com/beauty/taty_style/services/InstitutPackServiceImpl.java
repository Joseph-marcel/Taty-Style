package com.beauty.taty_style.services;



import org.springframework.stereotype.Service;

import com.beauty.taty_style.models.Allowance;
import com.beauty.taty_style.models.Brushing;
import com.beauty.taty_style.models.Closure;
import com.beauty.taty_style.models.Director;
import com.beauty.taty_style.models.DreadLocks;
import com.beauty.taty_style.models.Dyeing;
import com.beauty.taty_style.models.GraftInstall;
import com.beauty.taty_style.models.HairBun;
import com.beauty.taty_style.models.HairCut;
import com.beauty.taty_style.models.HairRemoval;
import com.beauty.taty_style.models.LayingWicks;
import com.beauty.taty_style.models.MakeUp;
import com.beauty.taty_style.models.Manicure;
import com.beauty.taty_style.models.Pack;
import com.beauty.taty_style.models.Pedicure;
import com.beauty.taty_style.models.Rastas;
import com.beauty.taty_style.models.Scrub;
import com.beauty.taty_style.models.Shampoo;
import com.beauty.taty_style.models.Straightening;
import com.beauty.taty_style.models.WeddingHairCut;
import com.beauty.taty_style.repositories.AllowanceRepository;
import com.beauty.taty_style.repositories.PackRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class InstitutPackServiceImpl implements InstitutPackservice{
	
	private PackRepository  packRepo;
	private AllowanceRepository allowanceRepo;

	
	@Override
	public Pack createPack(Pack pack) {
		// TODO Auto-generated method stub
		Pack pck = Director.packBuilder()
				           .allowances()
				           .bills()
				           .build();
		return packRepo.save(pck);
	}

	
	@Override
	public void addAllowanceInPack(Long packId,String name) {
		// TODO Auto-generated method stub
		Allowance existingAllowance = getAllowanceByName(name);
		Pack existingPack = getPackByPackId(packId);
		     existingPack.getAllowances().add(existingAllowance);
	}
	
	
	@Override
	public Pack getPackByPackId(Long packId) {
		// TODO Auto-generated method stub
		
		return packRepo.findById(packId).orElse(null);
	}

	
	@Override
	public void removeAllowanceInPack(Long packId,String name) {
		// TODO Auto-generated method stub
		Allowance existingAllowance = getAllowanceByName(name);
		Pack existingPack = getPackByPackId(packId);
		     existingPack.getAllowances().remove(existingAllowance);
	}


	@Override
	public Allowance getAllowanceByName(String name) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findByName(name);
	}

	
	@Override
	public Allowance createAllowance(Brushing brs) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(brs);
	}

	
	@Override
	public Allowance createAllowance(Closure cls) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(cls);
	}

	
	@Override
	public Allowance createAllowance(DreadLocks dread) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(dread);
	}

	
	@Override
	public Allowance createAllowance(Dyeing die) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(die);
	}

	
	@Override
	public Allowance createAllowance(GraftInstall graft) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(graft);
	}

	
	@Override
	public Allowance createAllowance(HairBun hairBun) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(hairBun);
	}

	
	@Override
	public Allowance createAllowance(HairCut hairCut) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Allowance createAllowance(HairRemoval hairR) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Allowance createAllowance(LayingWicks layW) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(layW);
	}


	
	@Override
	public Allowance createAllowance(MakeUp makeUp) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(makeUp);
	}

	
	@Override
	public Allowance createAllowance(Manicure manicure) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(manicure);
	}

	
	@Override
	public Allowance createAllowance(Pedicure pedicure) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(pedicure);
	}

	
	@Override
	public Allowance createAllowance(Rastas rastas) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(rastas);
	}

	
	@Override
	public Allowance createAllowance(Scrub scrub) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(scrub);
	}
	
	
	@Override
	public Allowance createAllowance(Shampoo shampoo) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(shampoo);
	}
	
	
	@Override
	public Allowance createAllowance(Straightening straightening) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(straightening);
	}


	
	@Override
	public Allowance createAllowance(WeddingHairCut weddingHairCut) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.save(weddingHairCut);
	}

}

package com.beauty.taty_style.controllers;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beauty.taty_style.models.Allowance;
import com.beauty.taty_style.models.Brushing;
import com.beauty.taty_style.models.Closure;
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
import com.beauty.taty_style.services.InstitutPackservice;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BillController {

	private InstitutPackservice packService;
	
	
	@PostMapping("/pack")
	public Pack createPack(@RequestBody Pack pack) {
		
		return packService.createPack(pack);
	}
	
	@PostMapping("/allowances/brushing")
	public Allowance createBrushing(@RequestBody Brushing brs) {
		
		return packService.createAllowance(brs);
	}
	
	@PostMapping("/allowances/closure")
	public Allowance createClosure(@RequestBody Closure cls) {
		
		return packService.createAllowance(cls);
	}
	
	@PostMapping("/allowances/dreadLocks")
	public Allowance createDreadLocks(@RequestBody DreadLocks dread) {
		
		return packService.createAllowance(dread);
	}
	
	@PostMapping("/allowances/dyeing")
	public Allowance createDyeing(@RequestBody Dyeing die) {
		
		return packService.createAllowance(die);
	}
	
	@PostMapping("/allowances/graftInstall")
	public Allowance createGraftInstall(@RequestBody GraftInstall graft) {
		
		return packService.createAllowance(graft);
	}
	
	@PostMapping("/allowances/hairBun")
	public Allowance createHairBun(@RequestBody HairBun hairBun) {
		
		return packService.createAllowance(hairBun);
	}
	
	@PostMapping("/allowances/hairCut")
	public Allowance createHairCut(@RequestBody HairCut hairCut) {
		
		return packService.createAllowance(hairCut);
	}
	
	@PostMapping("/allowances/hairRemoval")
	public Allowance createHairRemoval(@RequestBody HairRemoval hairR) {
		
		return packService.createAllowance(hairR);
	}
	
	@PostMapping("/allowances/layingWicks")
	public Allowance createLayingWicks(@RequestBody LayingWicks layW) {
		
		return packService.createAllowance(layW);
	}
	
	@PostMapping("/allowances/makeUp")
	public Allowance createMakeUp(@RequestBody MakeUp makeUp) {
		
		return packService.createAllowance(makeUp);
	}
	
	@PostMapping("/allowances/manicure")
	public Allowance createManicure(@RequestBody Manicure manicure) {
		
		return packService.createAllowance(manicure);
	}
	
	@PostMapping("/allowances/pedicure")
	public Allowance createPedicure(@RequestBody Pedicure pedicure) {
		
		return packService.createAllowance(pedicure);
	}
	
	@PostMapping("/allowances/rastas")
	public Allowance createRastas(@RequestBody Rastas rastas) {
		
		return packService.createAllowance(rastas);
	}
	
	@PostMapping("/allowances/scrub")
	public Allowance createScrub(@RequestBody Scrub scrub) {
		
		return packService.createAllowance(scrub);
	}
	
	@PostMapping("/allowances/shampoo")
	public Allowance createShampoo(@RequestBody Shampoo shampoo) {
		
		return packService.createAllowance(shampoo);
	}
	
	@PostMapping("/allowances/straightening")
	public Allowance createStraightening(@RequestBody Straightening straightening) {
		
		return packService.createAllowance(straightening);
	}
	
	@PostMapping("/allowances/weddingHaircut")
	public Allowance createWeddingHairCut(@RequestBody WeddingHairCut weddingHairCut) {
		
		return packService.createAllowance(weddingHairCut);
	}
	
	@PutMapping("/pack/add/{packId}/{name}")
	public void addAllowanceToPack(@PathVariable Long packId,@PathVariable String name) {
		packService.addAllowanceInPack(packId, name);
	}
	
	@PutMapping("/pack/remove/{packId}/{name}")
	public void RemoveAllowanceFromPack(@PathVariable Long packId,@PathVariable String name) {
		packService.removeAllowanceInPack(packId, name);
	}
}

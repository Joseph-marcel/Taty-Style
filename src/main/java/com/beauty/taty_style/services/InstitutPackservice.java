package com.beauty.taty_style.services;

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


public interface InstitutPackservice {
	
	Pack createPack(Pack pack);
	Pack getPackByPackId(Long packId);
	void addAllowanceInPack(Long packId,String name);
	void removeAllowanceInPack(Long packId,String name);
	Allowance createAllowance(Brushing brs);
	Allowance createAllowance(Closure cls);
	Allowance createAllowance(DreadLocks dread);
	Allowance createAllowance(Dyeing die);
	Allowance createAllowance(GraftInstall graft);
	Allowance createAllowance(HairBun hairBun);
	Allowance createAllowance(HairCut hairCut);
	Allowance createAllowance(HairRemoval hairR);
	Allowance createAllowance(LayingWicks layW);
	Allowance createAllowance(MakeUp makeUp);
	Allowance createAllowance(Manicure manicure);
	Allowance createAllowance(Pedicure pedicure);
	Allowance createAllowance(Rastas rastas);
	Allowance createAllowance(Scrub scrub);
	Allowance createAllowance(Shampoo shampoo);
	Allowance createAllowance(Straightening straightening);
	Allowance createAllowance(WeddingHairCut weddingHairCut);
	Allowance getAllowanceByName(String name);

}

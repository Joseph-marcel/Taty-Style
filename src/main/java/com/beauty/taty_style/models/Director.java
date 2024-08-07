package com.beauty.taty_style.models;

import com.beauty.taty_style.models.Bill.BillBuilder;
import com.beauty.taty_style.models.Brushing.BrushingBuilder;
import com.beauty.taty_style.models.Closure.ClosureBuilder;
import com.beauty.taty_style.models.Customer.CustomerBuilder;
import com.beauty.taty_style.models.DreadLocks.DreadLocksBuilder;
import com.beauty.taty_style.models.Dyeing.DyeingBuilder;
import com.beauty.taty_style.models.GraftInstall.GraftInstallBuilder;
import com.beauty.taty_style.models.HairBun.HairBunBuilder;
import com.beauty.taty_style.models.HairRemoval.HairRemovalBuilder;
import com.beauty.taty_style.models.Haircut.HaircutBuilder;
import com.beauty.taty_style.models.LayingWicks.LayingWicksBuilder;
import com.beauty.taty_style.models.MakeUp.MakeUpBuilder;
import com.beauty.taty_style.models.Manicure.ManicureBuilder;
import com.beauty.taty_style.models.Pack.PackBuilder;
import com.beauty.taty_style.models.Pedicure.PedicureBuilder;
import com.beauty.taty_style.models.Rastas.RastasBuilder;
import com.beauty.taty_style.models.WeddingHairCut.WeddingHairCutBuilder;

public class Director {
	
	   public static Pack.PackBuilder packBuilder(){
		
		    return new PackBuilder();
	    }
	   
	   
	   public static Customer.CustomerBuilder customerBuilder(){
			
		    return new CustomerBuilder();
	    }
	   
	   
	   public static Bill.BillBuilder billBuilder(){
			
		    return new BillBuilder();
	    }
	   
	   
	   public static Brushing.BrushingBuilder brushingBuilder(){
			
		    return new BrushingBuilder();
	    }
	   
	   
	   public static Closure.ClosureBuilder closureBuilder(){
			
		    return new ClosureBuilder();
	    }
	   
	   
	   public static DreadLocks.DreadLocksBuilder dreadLocksBuilder(){
			
		    return new DreadLocksBuilder();
	    }
	   
	   
	   public static Dyeing.DyeingBuilder dyeingBuilder(){
			
		    return new DyeingBuilder();
	    }
	   
	   
	   public static HairBun.HairBunBuilder hairBunBuilder(){
			
		    return new HairBunBuilder();
	    }
	   
	   
	   public static GraftInstall.GraftInstallBuilder graftInstallBuilder(){
			
		    return new GraftInstallBuilder();
	    }
	   
	   
	   public static Haircut.HaircutBuilder haircutBuilder(){
			
		    return new HaircutBuilder();
	    }
	   
	   
	   public static HairRemoval.HairRemovalBuilder hairRemovalBuilder(){
			
		    return new HairRemovalBuilder();
	    }
	   
	   
	   public static LayingWicks.LayingWicksBuilder layingWicksBuilder(){
			
		    return new LayingWicksBuilder();
	    }
	   
	   public static  MakeUp.MakeUpBuilder  makeUpBuilder(){
			
		    return new  MakeUpBuilder();
	    }
	   
	   
	   public static  Manicure.ManicureBuilder  manicureBuilder(){
			
		    return new  ManicureBuilder();
	    }
	   
	   public static  Pedicure.PedicureBuilder  pedicureBuilder(){
			
		    return new  PedicureBuilder();
	    }
	   
	   public static  Rastas.RastasBuilder  rastasBuilder(){
			
		    return new  RastasBuilder();
	    }
	   
	   
	   public static  WeddingHairCut.WeddingHairCutBuilder  weddingHairCutBuilder(){
			
		    return new  WeddingHairCutBuilder();
	    }
	   
	   
	   
	   
	   
	   
	   
}

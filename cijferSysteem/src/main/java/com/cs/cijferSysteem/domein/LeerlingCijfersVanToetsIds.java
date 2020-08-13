package com.cs.cijferSysteem.domein;

import java.util.List;

import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.controller.ToetsService;

public class LeerlingCijfersVanToetsIds {
	Long leerlingid;
	List<Float> cijfers;
	
	ToetsService ts;
	LeerlingService ls;
	
	public LeerlingCijfersVanToetsIds(Leerling leerling, Iterable<Toets> toetsen){
		this.leerlingid = leerling.getId();
		for(Toets t:  toetsen) {
			List<Cijfer> cijfers = t.getCijfers();
			boolean gevonden = false;
			for(Cijfer c : cijfers) {
				if(c.getLeerling().getId() == leerlingid) {
					this.cijfers.add(c.getCijfer());
					gevonden = true;
					break;
				}
			}
			if(!gevonden) {
				//Registreer voor elke toets wel een cijfer, ter voorkoming van verschuiving
				this.cijfers.add(null);
			}
		}
	}
}

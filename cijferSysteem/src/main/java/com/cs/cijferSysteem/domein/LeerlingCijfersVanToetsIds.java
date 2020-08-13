package com.cs.cijferSysteem.domein;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.controller.ToetsService;

public class LeerlingCijfersVanToetsIds {
	Long leerlingid;
	List<Float> cijfers = new ArrayList<Float>();
	
	@Autowired
	ToetsService ts;
	@Autowired
	LeerlingService ls;
	
	public LeerlingCijfersVanToetsIds(Leerling leerling, Iterable<Toets> toetsen){
		System.out.println("Object aanmaken");
		this.leerlingid = leerling.getId();
		for(Toets t:  toetsen) {
			List<Cijfer> cijfers = t.getCijfers();
			boolean gevonden = false;
			for(Cijfer c : cijfers) {
				if(c.getLeerling().getId() == leerlingid) {
					this.cijfers.add(c.getCijfer());
					System.out.println(this.cijfers);
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

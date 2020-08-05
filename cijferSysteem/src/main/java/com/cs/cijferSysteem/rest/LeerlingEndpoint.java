package com.cs.cijferSysteem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.domein.Leerling;

@RestController
public class LeerlingEndpoint {


	@Autowired
	LeerlingService ls;


	@GetMapping("/leerlingOverzicht")
	public Iterable<Leerling> geefOverzichtLeerling() {
		System.out.println("Overzicht");
		return ls.laatLeerlingZien();
	}

	@PostMapping("/api/maakLeerling")
	public void maakLeerlingAan(@RequestBody Leerling leerling){
		ls.maakLeerling(leerling);
		System.out.println(leerling.getVoornaam());
		System.out.println(leerling.getAchternaam());
		System.out.println(leerling.getLeerlingNummer());
		System.out.println(leerling.getGeboorteDatum());
	}



}


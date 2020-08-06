package com.cs.cijferSysteem.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.domein.Leerling;

@RestController
public class LeerlingEndpoint {


	@Autowired
	LeerlingService ls;


	@GetMapping("/leerlingOverzicht")
	public Iterable<Leerling> geefOverzichtLeerling() {
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


	@GetMapping("/leerling/{id}")
	public Optional<Leerling> getLeerlingById(@PathVariable("id") Long id){
		System.out.println("id = " + id);
		return ls.toonLeerling(id);
	}

}


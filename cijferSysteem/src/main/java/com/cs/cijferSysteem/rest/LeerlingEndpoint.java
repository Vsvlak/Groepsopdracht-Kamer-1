package com.cs.cijferSysteem.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.cs.cijferSysteem.dto.CreateLeerlingDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.domein.Leerling;
import com.cs.cijferSysteem.domein.ToetsCijfer;

@RestController
public class LeerlingEndpoint {

	@Autowired
	LeerlingService ls;

	@GetMapping("/leerlingOverzicht")
	public Iterable<Leerling> geefOverzichtLeerling() {
		return ls.laatLeerlingZien();
	}

	@PostMapping("/api/maakLeerling")
	public void maakLeerlingAan(@RequestBody CreateLeerlingDto createLeerlingDto){
		Leerling leerling = new Leerling();
		leerling.setVoornaam(createLeerlingDto.getVoornaam());
		leerling.setAchternaam(createLeerlingDto.getAchternaam());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		leerling.setGeboorteDatum(LocalDate.parse(createLeerlingDto.getGeboortedatum(), formatter));
		this.ls.save(leerling);
	}

	@GetMapping("/leerling/{id}")
	public Optional<Leerling> getLeerlingById(@PathVariable("id") Long id){
		return ls.toonLeerling(id);
	}
	
	@GetMapping("/cijfersVanLeerling/{id}")
	public List<ToetsCijfer> toonCijfersVanLeerling(@PathVariable("id") Long id){
		return ls.toonLeerling(id).get().getCijfers();
	}
	
//	@GetMapping("/leerlingOverzicht/{start}/{end}")
//	public List<Leerling> search(@PathVariable("start") int start, @PathVariable("end") int end){ 
//		return ls.search(start, end);
//		
//	}
}


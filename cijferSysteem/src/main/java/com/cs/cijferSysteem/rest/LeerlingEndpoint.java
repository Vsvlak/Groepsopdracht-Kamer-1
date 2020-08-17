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
import com.cs.cijferSysteem.domein.Cijfer;
import com.cs.cijferSysteem.domein.Docent;

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
		leerling.setLeerlingNummer(Integer.valueOf(createLeerlingDto.getLeerlingnummer()));
		this.ls.save(leerling);
	}

	@GetMapping("/leerling/{id}")
	public Optional<Leerling> getLeerlingById(@PathVariable("id") Long id){
		return ls.toonLeerling(id);
	}
	
	@PostMapping("api/editLeerling/{id}")
	public void update(@RequestBody CreateLeerlingDto createLeerlingDto, @PathVariable("id") Long id) { 
		Leerling leerling = getLeerlingById(id).get();
		leerling.setVoornaam(createLeerlingDto.getVoornaam());
		leerling.setAchternaam(createLeerlingDto.getAchternaam());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		leerling.setGeboorteDatum(LocalDate.parse(createLeerlingDto.getGeboortedatum(), formatter));
		leerling.setLeerlingNummer(Integer.valueOf(createLeerlingDto.getLeerlingnummer()));
		this.ls.save(leerling);
	}
	
	@GetMapping("/cijfersVanLeerling/{id}")
	public List<Cijfer> toonCijfersVanLeerling(@PathVariable("id") Long id){
		return ls.toonLeerling(id).get().getCijfers();
	}
	
	///////////!!!!
//	ENDPOINT VOOR LEERLINGCIJFERPER VAK (toetsVakCijfer nodig.)
//	@GetMapping("LeerlingVakkenToetsenCijfersOverzicht/{id}")
//	public List<ToetsCijferPerVak> toonLeerlingVakkenToetsCijfer(@PathVariable("id") Long id){ 
//		return ls.toonLeerling(id).get().getCijfersPerVak();
//	}
	
	
	
	
	
	//oud voorbeeld:
//	@GetMapping("/leerlingOverzicht/{voornaam}")
//	public List<Leerling> search(@PathVariable("voornaam") String voornaam) {
//		return ls.search(voornaam);

//	@GetMapping("/leerlingOverzicht/{start}/{end}")
//	public List<Leerling> search(@PathVariable("start") int start, @PathVariable("end") int end){ 
//		return ls.search(start, end);
//		

//	}
}


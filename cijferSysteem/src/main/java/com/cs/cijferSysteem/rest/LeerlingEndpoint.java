package com.cs.cijferSysteem.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Stream;
import com.cs.cijferSysteem.dto.CijferDto;
import com.cs.cijferSysteem.dto.CreateLeerlingDto;
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
	public Stream<CreateLeerlingDto> geefOverzichtLeerling() {
		return ls.laatLeerlingZien().stream().map(l -> new CreateLeerlingDto(l.getId(), l.getVoornaam(), l.getAchternaam(), l.getGeboorteDatum().toString()));
	}

	@GetMapping("/leerling/{id}")
	public Optional<CreateLeerlingDto> getLeerlingById(@PathVariable("id") Long id){
		return ls.toonLeerling(id).map(l -> new CreateLeerlingDto(l.getId(), l.getAchternaam(), l.getVoornaam(), l.getGeboorteDatum().toString()));
	}
	
	@GetMapping("/cijfersVanLeerling/{id}")
	public Stream<CijferDto> toonCijfersVanLeerling(@PathVariable("id") Long id){
		return ls.toonLeerling(id).get().getCijfers().stream().map(c -> new CijferDto(c.getId(), c.getCijfer()));
	}
	
	@PostMapping("/api/maakLeerling")
	public void maakLeerlingAan(@RequestBody CreateLeerlingDto createLeerlingDto){
		Leerling leerling = new Leerling();
		leerling.setVoornaam(createLeerlingDto.getVoornaam());
		leerling.setAchternaam(createLeerlingDto.getAchternaam());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		leerling.setGeboorteDatum(LocalDate.parse(createLeerlingDto.getGeboortedatum(), formatter));
		try{
			leerling.setLeerlingNummer(Integer.valueOf(createLeerlingDto.getLeerlingnummer()));
		}catch (Exception e){
			System.out.println("Wij vullen het leerlingnummer niet zelf in. Deze wordt gegenereerd als id");
		}
		this.ls.save(leerling);
	}


	
//	@GetMapping("/leerlingOverzicht/{voornaam}")
//	public List<Leerling> search(@PathVariable("voornaam") String voornaam) {
//		return ls.search(voornaam);

//	@GetMapping("/leerlingOverzicht/{start}/{end}")
//	public List<Leerling> search(@PathVariable("start") int start, @PathVariable("end") int end){ 
//		return ls.search(start, end);
//		

//	}

}


package com.cs.cijferSysteem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.KlasService;
import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.domein.Klas;
import com.cs.cijferSysteem.domein.Leerling;
import com.cs.cijferSysteem.dto.KlasLeerlingDto;

@RestController
public class KlasEndpoint {

	@Autowired
	KlasService ks;
	@Autowired
	LeerlingService ls;	
	
	@GetMapping("/klassenOverzicht")
	public Iterable<Klas> toonKlassenOverzicht(){
		return ks.laatKlasZien();
	}
	
	@GetMapping("/leerlingenInKlas/{id}")
	public List<Leerling> toonLeerlingenVanKlas(@PathVariable("id") Long id){
		return ks.getKlasById(id).get().getLeerlingen();
	}
	
	@PostMapping("/api/maakKlas")
	public void maakVak(@RequestBody Klas k) {
		ks.update(k);
	}
	
	@PostMapping("/api/voegLeerlingToe")
	public void voegLeerlingToe(@RequestBody KlasLeerlingDto klasLeerlingDto) {
		Klas k = ks.getKlasById(klasLeerlingDto.getKlasid()).get();
		Leerling l = ls.toonLeerling(klasLeerlingDto.getLeerlingid()).get();
		//k.voegLeerlingToe(l);
		k.getLeerlingen().add(l);
		ks.update(k);
	}
}

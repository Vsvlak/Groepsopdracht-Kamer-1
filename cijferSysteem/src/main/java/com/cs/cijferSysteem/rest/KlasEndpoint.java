package com.cs.cijferSysteem.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.KlasService;
import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.controller.VakService;
import com.cs.cijferSysteem.domein.Docent;
import com.cs.cijferSysteem.domein.Klas;
import com.cs.cijferSysteem.domein.Leerling;
import com.cs.cijferSysteem.domein.Vak;
import com.cs.cijferSysteem.dto.KlasLeerlingDto;
import com.cs.cijferSysteem.dto.KlasVakDto;

@RestController
public class KlasEndpoint {

	@Autowired
	KlasService ks;
	@Autowired
	LeerlingService ls;	
	@Autowired
	VakService vs;	
	
	@GetMapping("/klassenOverzicht")
	public Iterable<Klas> toonKlassenOverzicht(){
		return ks.laatKlasZien();
	}
	
	// hieronder check
	@GetMapping("/klassen/{id}")
	public Optional<Klas> getKlasById(@PathVariable("id")Long id){ 
		return ks.getKlasById(id);
	}
	@GetMapping("/leerlingenInKlas/{id}")
	public List<Leerling> toonLeerlingenVanKlas(@PathVariable("id") Long id){
		return ks.getKlasById(id).get().getLeerlingen();
	}
	
	@GetMapping("/vakkenVanKlas/{id}")
	public List<Vak> toonVakkenVanKlas(@PathVariable("id") Long id){
		return ks.getKlasById(id).get().getVakken();
	}
	
	@PostMapping("/api/maakKlas")
	public void maakVak(@RequestBody Klas k) {
		ks.update(k);
	}
	
	@PostMapping("/api/voegLeerlingToe")
	public void voegLeerlingToe(@RequestBody KlasLeerlingDto klasLeerlingDto) {
		Klas k = ks.getKlasById(klasLeerlingDto.getKlasid()).get();
		Leerling l = ls.toonLeerling(klasLeerlingDto.getLeerlingid()).get();
		if(!k.getLeerlingen().contains(l)) {
			k.getLeerlingen().add(l);
			ks.update(k);
		}
	}
	
	@PostMapping("/api/voegVakToe")
	public void voegVakToe(@RequestBody KlasVakDto klasVakDto) {
		Klas k = ks.getKlasById(klasVakDto.getKlasid()).get();
		Vak v = vs.getVakById(klasVakDto.getVakid()).get();
		if(!k.getVakken().contains(v)) {
			k.getVakken().add(v);
			ks.update(k);
		}
	}
}

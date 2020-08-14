package com.cs.cijferSysteem.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.DocentService;
import com.cs.cijferSysteem.controller.VakService;
import com.cs.cijferSysteem.domein.Docent;
import com.cs.cijferSysteem.domein.DocentVak;
import com.cs.cijferSysteem.domein.Vak;
import com.cs.cijferSysteem.dto.DocentVakkenDto;


@RestController
public class DocentEndpoint {
	@Autowired
	DocentService ds;
	@Autowired
	VakService vs;

	@GetMapping("/docentOverzicht")
	public Iterable<Docent> geefOverzichtDocent() {
		return ds.laatDocentZien();
	}
	
	@PostMapping("api/maakDocent")
	public void maakDocent(@RequestBody Docent docent) { 
		ds.maakDocent(docent);
	}
	
	//Voegt vak toe aan docent
	@PostMapping("/api/maakDocentAanVak")
	public void maakDocent(@RequestBody DocentVakkenDto docentVakkenDto) { 
		Docent d = ds.toonDocentById(docentVakkenDto.getDocentid()).get();
		Vak v = vs.toonVakById(docentVakkenDto.getVakid()).get();
		d.getVakken().add(v);
		ds.update(d);

	}
	
	@PostMapping("/api/voegDocentVakToeAanDocent/{docentid}")
	public void voegDocentVakToeAanDocent(@PathVariable("docentid") Long docentid, @RequestBody DocentVak dv) {
		ds.toonDocentById(docentid).get().voegDocentVakToe(dv);
	}
	
	@GetMapping("/docent/{id}")
	public Optional<Docent> getDocentById(@PathVariable("id")Long id){ 
		return ds.toonDocentById(id);
	}
	
	@GetMapping("/vakkenVanDocent/{docentid}")
	public List<Vak> getVakvanDocent(@PathVariable("docentid")Long docentid){ 
		return ds.getDocentById(docentid).get().getVakken();
	}
}


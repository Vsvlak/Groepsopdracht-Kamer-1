package com.cs.cijferSysteem.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cs.cijferSysteem.domein.Toets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.VakService;
import com.cs.cijferSysteem.domein.Vak;
import com.cs.cijferSysteem.dto.VakDto;

@RestController
public class VakEndpoint {
	
	@Autowired
	VakService vs;
	
	@GetMapping("/vakkenOverzicht")
	public List<VakDto> toonVakkenOverzicht(){
		List<VakDto> vakdtos = new ArrayList<>();
		Iterable<Vak> vakken = vs.laatVakZien();
		for(Vak v : vakken) {
			VakDto dto = new VakDto();
			dto.setId(v.getId());
			dto.setNaam(v.getNaam());
			vakdtos.add(dto);
		}
		return vakdtos;
	}

	@PostMapping("/api/maakVak")
	public void maakVak(@RequestBody Vak v) {
		vs.maakVak(v);
	}
	
	@GetMapping("/vak/{id}")
	public Optional<Vak> getVakById(@PathVariable("id") Long id){
		return vs.getVakById(id);
	}

	@GetMapping("/toetsenVanVak/{vakId}")
	public List<Toets> toonToetsenVanVak(@PathVariable("vakId") Long id){
		return vs.getVakById(id).get().getToetsen();
	}
}


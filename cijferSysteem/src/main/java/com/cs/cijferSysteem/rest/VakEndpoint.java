package com.cs.cijferSysteem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.VakService;
import com.cs.cijferSysteem.domein.Vak;

@RestController
public class VakEndpoint {
	
	@Autowired
	VakService vs;
	
	@GetMapping("/vakkenOverzicht")
	public Iterable<Vak> toonVakkenOverzicht(){
		return vs.laatVakZien();
	}
	
	@PostMapping("/api/maakVak")
	public void maakVak(@RequestBody Vak v) {
		vs.maakVak(v);
	}
}

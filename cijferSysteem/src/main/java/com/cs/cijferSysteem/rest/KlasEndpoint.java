package com.cs.cijferSysteem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.KlasService;
import com.cs.cijferSysteem.domein.Klas;

@RestController
public class KlasEndpoint {

	@Autowired
	KlasService ks;
	
	@GetMapping("/klassenOverzicht")
	public Iterable<Klas> toonKlassenOverzicht(){
		System.out.println("Klassen overzicht getoond");
		return ks.laatKlassenZien();
	}
	
	@PostMapping("/api/maakKlas")
	public void maakVak(@RequestBody Klas k) {
		ks.maakKlas(k);
	}
}

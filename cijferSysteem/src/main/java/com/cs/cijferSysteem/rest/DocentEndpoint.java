package com.cs.cijferSysteem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.DocentService;
import com.cs.cijferSysteem.domein.Docent;

@RestController
public class DocentEndpoint {

	

	@Autowired
	DocentService ds;


	@GetMapping("/docentOverzicht")
	public Iterable<Docent> geefOverzichtDocent() {
		return ds.laatDocentZien();
	}
	
	@PostMapping("api/maakDocent")
	public void maakDocent(@RequestBody Docent docent) { 
		ds.maakDocent(docent);

	}

}

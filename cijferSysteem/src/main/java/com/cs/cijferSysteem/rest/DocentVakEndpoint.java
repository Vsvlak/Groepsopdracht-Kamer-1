package com.cs.cijferSysteem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.DocentVakService;
import com.cs.cijferSysteem.domein.DocentVak;

@RestController
public class DocentVakEndpoint {
	@Autowired
	DocentVakService dvs;
	
	@GetMapping("/docentVakOverzicht")
	public Iterable<DocentVak> geefDocentVakOverzicht(){
		return dvs.laatDocentVakkenZien();
	}

}

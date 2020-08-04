package com.cs.cijferSysteem.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.leerlingservice;
import com.cs.cijferSysteem.domein.Leerling;

@RestController
public class Oefenclass {

	@Autowired
	leerlingservice ls;
	
	@GetMapping("/api/oefen/{id}")
	public Leerling LeerlingIdTeruggeven(@PathVariable("id")long id, 
			@RequestParam(value = "a",required = false, defaultValue = "12")int a) { 
		System.out.println("werktdit?");
		System.out.println(a);
		//ls.toonAlleLeerlingen(); // nog leerling per id kunnen aanroepen 
		Leerling l = new Leerling();
		l.setId(a);
		return l;

	}
	@PostMapping("api/createLeerling") // requestBody bij meer info en niet losse parameters
	public void createLeerling(@RequestBody Leerling leerling){
		System.out.println(leerling.getNaam());
	}
	
	
//	@GetMapping("/leerlingOverzicht")
//	public Leerling probeerverbindingtemaken() {
//		System.out.println("HIJ DOET HET");
//		
//		return new Leerling();
//		
//	}
//	
}

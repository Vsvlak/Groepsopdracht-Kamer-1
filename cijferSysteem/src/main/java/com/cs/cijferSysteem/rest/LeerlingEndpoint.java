package com.cs.cijferSysteem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.leerlingservice;
import com.cs.cijferSysteem.domein.Leerling;

@RestController
public class LeerlingEndpoint {

	
	@Autowired
	leerlingservice ls;
	
	@GetMapping("/leerlingOverzicht")
	public Leerling probeerverbindingtemaken() {
		System.out.println("HIJ DOET HET");
		
		return new Leerling();
		
	}
	
	@GetMapping("/leerlingOverzicht2")
	public String probeerverbindingtemaken2() { 
		System.out.println("hoi ik werk");
		return "hoi <b>fijn</b> dat je <input> er bent";
	}
	

	@GetMapping("/nunetjes")
	public Iterable <Leerling> endpontmethode(){ 
		return ls.nunetjes();
		
	}
}


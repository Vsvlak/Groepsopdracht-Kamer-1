package com.cs.cijferSysteem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.domein.Leerling;

@RestController
public class LeerlingEndpoint {


	@Autowired
	LeerlingService ls;


	@GetMapping("/leerlingOverzicht")
	public Leerling probeerverbindingtemaken() {
		System.out.println("Overzicht");
		return new Leerling();

	}

}


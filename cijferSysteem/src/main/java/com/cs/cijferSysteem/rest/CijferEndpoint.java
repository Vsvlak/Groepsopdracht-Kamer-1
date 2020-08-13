package com.cs.cijferSysteem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.CijferService;
import com.cs.cijferSysteem.domein.Cijfer;

@RestController
public class CijferEndpoint {
	
	@Autowired
	CijferService tcs;
	
    @GetMapping("/toetsCijferOverzicht")
    public Iterable<Cijfer> geefOverzichtToetsCijfering() {
        return tcs.laatToetsCijfersZien();
    }
    
}

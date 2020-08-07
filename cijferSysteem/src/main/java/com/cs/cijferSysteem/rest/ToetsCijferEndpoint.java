package com.cs.cijferSysteem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.ToetsCijferService;
import com.cs.cijferSysteem.domein.ToetsCijfer;

@RestController
public class ToetsCijferEndpoint {
	
	@Autowired
	ToetsCijferService tcs;
	
    @GetMapping("/toetsCijferOverzicht")
    public Iterable<ToetsCijfer> geefOverzichtToetsCijfering() {
        return tcs.laatToetsCijfersZien();
    }
}
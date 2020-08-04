package com.cs.cijferSysteem.controller;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.cijferSysteem.domein.Leerling;

@Service
@Transactional
public class LeerlingService {


	@Autowired
	LeerlingRepository lr;
	
	public void maakLeerling(){
		Leerling leerling = new Leerling();
		lr.save(leerling);
		}


	public Iterable<Leerling> laatLeerlingZien(){
		return lr.findAll();
	}



}
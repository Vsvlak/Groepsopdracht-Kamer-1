package com.cs.cijferSysteem.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.cijferSysteem.domein.Leerling;

@Service
@Transactional
public class LeerlingService {


	@Autowired
	LeerlingRepository lr;
	
<<<<<<< HEAD
=======
	public void maakLeerling(){
		Leerling leerling = new Leerling();
		lr.save(leerling);
	}
>>>>>>> master


	public void maakLeerling(Leerling leerling){
		lr.save(leerling);
	}

	public Iterable<Leerling> laatLeerlingZien(){
		return lr.findAll();
	}





}
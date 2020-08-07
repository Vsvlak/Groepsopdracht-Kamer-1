package com.cs.cijferSysteem.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.cijferSysteem.domein.Leerling;

@Service
@Transactional
public class LeerlingService {

	@Autowired
	LeerlingRepository lr;
	
	public void save(Leerling leerling){
		lr.save(leerling);
	}

	public Iterable<Leerling> laatLeerlingZien(){
		return lr.findAll();
	}
	
	public Optional<Leerling> toonLeerling(Long id) {
		return lr.findById(id);
	}
}
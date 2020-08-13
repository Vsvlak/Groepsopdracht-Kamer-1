package com.cs.cijferSysteem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.cijferSysteem.domein.Cijfer;

@Service
@Transactional
public class ToetsCijferService {
	
	@Autowired
	ToetsCijferRepository tcr;
	
	public void save(Cijfer tc) {
		tcr.save(tc);
	}
	
	public Optional<Cijfer> getToetsCijferById(Long id){
		return tcr.findById(id);
	}
	
	public Iterable<Cijfer> laatToetsCijfersZien(){
		return tcr.findAll();
	}
}

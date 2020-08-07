package com.cs.cijferSysteem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.cijferSysteem.domein.ToetsCijfer;

@Service
@Transactional
public class ToetsCijferService {
	
	@Autowired
	ToetsCijferRepository tcr;
	
	public void save(ToetsCijfer tc) {
		tcr.save(tc);
	}
	
	public Optional<ToetsCijfer> getToetsCijferById(Long id){
		return tcr.findById(id);
	}
	
	public Iterable<ToetsCijfer> laatToetsCijfersZien(){
		return tcr.findAll();
	}
}

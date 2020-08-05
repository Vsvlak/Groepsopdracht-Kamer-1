package com.cs.cijferSysteem.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.cijferSysteem.domein.Vak;

@Service
@Transactional
public class VakService {
	
	@Autowired
	VakRepository vr;
	
	public void maakVak() {
		Vak vak = new Vak();
		vak.setNaam("Engels");
	}
	
	public void maakVak(Vak v) {
		vr.save(v);
	}
	
	public Iterable<Vak> laatVakkenZien(){
		return vr.findAll();
	}
}

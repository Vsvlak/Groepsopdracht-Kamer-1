package com.cs.cijferSysteem.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.cijferSysteem.domein.Vak;

@Service
@Transactional
public class VakService {
	
	@Autowired
	VakRepository vr;
	
	public void maakVak(Vak v) {
		vr.save(v);
	}
	
	public Iterable<Vak> laatVakkenZien(){
		return vr.findAll();
	}
	
	public Optional<Vak> getVakById(Long id) {
		return vr.findById(id);
	}
}

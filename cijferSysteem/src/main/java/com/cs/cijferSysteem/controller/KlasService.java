package com.cs.cijferSysteem.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.cijferSysteem.domein.Klas;

@Service
@Transactional
public class KlasService {

	@Autowired
	KlasRepository kr;
	
	public void maakKlas(Klas k) {
		kr.save(k);
	}

	public Iterable<Klas> laatKlassenZien(){
		return kr.findAll();
	}
}

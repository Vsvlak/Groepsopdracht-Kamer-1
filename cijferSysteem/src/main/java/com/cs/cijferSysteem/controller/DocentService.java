package com.cs.cijferSysteem.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.cijferSysteem.domein.Docent;

@Service
@Transactional
public class DocentService {

	@Autowired
	DocentRepository dr;

	public void maakDocent(Docent docent){
		dr.save(docent);
	}

	public Iterable<Docent> laatDocentZien(){
		return dr.findAll();
	}
}

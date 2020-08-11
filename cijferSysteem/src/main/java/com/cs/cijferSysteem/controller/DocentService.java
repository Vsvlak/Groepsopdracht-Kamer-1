package com.cs.cijferSysteem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cs.cijferSysteem.domein.Docent;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

	public Optional<Docent> getDocentById(Long id) {
		return dr.findById(id);
	}

	@Transactional(readOnly = false)
	public void update(Docent d) {
		dr.save(d);
	}
}

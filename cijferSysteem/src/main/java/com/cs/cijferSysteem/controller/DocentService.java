package com.cs.cijferSysteem.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.cijferSysteem.domein.Docent;


@Service
@Transactional
public class DocentService {


		@Autowired
		DocentRepository dr;

		@Transactional///(readOnly = false)
		public void update(Docent d){
			dr.save(d);
		}

	public void maakDocent(Docent docent){
		dr.save(docent);
	}


		public Optional<Docent> toonDocentById(Long id){
			return dr.findById(id);
		}


	public Iterable<Docent> laatDocentZien(){
		return dr.findAll();
	}

}

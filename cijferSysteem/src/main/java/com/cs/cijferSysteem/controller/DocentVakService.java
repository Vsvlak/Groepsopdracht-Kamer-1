package com.cs.cijferSysteem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.cijferSysteem.domein.DocentVak;

@Service
@Transactional
public class DocentVakService {
	@Autowired DocentVakRepository dvr;
	
	public void save(DocentVak dv) {
		dvr.save(dv);
	}
	
	public Optional<DocentVak> getDocentVakById(Long id){
		return dvr.findById(id);
	}
	
	public Iterable<DocentVak> laatDocentVakkenZien(){
		return dvr.findAll();
	}
	
	public DocentVak getByDocentIdAndVakId(Long docentid, Long vakid){
		return dvr.findByDocentIdAndVakId(docentid, vakid);
	}
}

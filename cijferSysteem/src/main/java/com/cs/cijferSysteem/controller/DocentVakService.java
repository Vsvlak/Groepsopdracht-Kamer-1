package com.cs.cijferSysteem.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.cijferSysteem.domein.Docentvak;
import com.cs.cijferSysteem.dto.KlasDto;

@Service
@Transactional
public class DocentVakService {
	@Autowired DocentVakRepository dvr;
	
	public void save(Docentvak dv) {
		dvr.save(dv);
	}
	
	public Optional<Docentvak> getDocentVakById(Long id){
		return dvr.findById(id);
	}
	
	public List<Docentvak> laatDocentVakkenZien(){
		return dvr.findAll();
	}
	
	public Docentvak getByDocentIdAndVakId(Long docentid, Long vakid){
		return dvr.findByDocentIdAndVakId(docentid, vakid);
	}
	
	public List<Docentvak> getByDocentId(Long docentid){
		return dvr.findByDocentId(docentid);
	}
	
	public List<Docentvak> getByVakId(Long vakid){
		return dvr.findByVakId(vakid);
	}
}

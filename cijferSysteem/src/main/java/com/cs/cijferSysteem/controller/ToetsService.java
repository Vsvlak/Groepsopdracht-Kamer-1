package com.cs.cijferSysteem.controller;

import com.cs.cijferSysteem.domein.Toets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ToetsService {
    
	@Autowired
    ToetsRepository tr;

    public Toets save(Toets toets){
        return tr.save(toets);
    }

    public Iterable<Toets> laatToetsZien(){
        return tr.findAll();
    }

    public Optional<Toets> toonToets(Long id) {
        return tr.findById(id);
    }
    
    public Iterable<Toets> findToetsByDocentvak(Long docentvakid){
    	return tr.findByDocentvakId(docentvakid);
    }
    
    public Iterable<Toets> findToetsByDocentvakAndKlas(Long docentvakid, Long klasid){
    	return tr.findByDocentvakIdAndKlasId(docentvakid, klasid);
    }
    
    public Iterable<Toets> findToetsByKlas(Long klasid){
    	return tr.findByKlasId(klasid);
    }

}

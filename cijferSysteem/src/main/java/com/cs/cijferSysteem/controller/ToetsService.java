package com.cs.cijferSysteem.controller;


import com.cs.cijferSysteem.domein.Docent;
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

    public Iterable<Toets> toonToetsenVan(Long docentid, Long vakId){
    	return tr.findByDocentIdAndVakId(docentid, vakId);
    }   
}

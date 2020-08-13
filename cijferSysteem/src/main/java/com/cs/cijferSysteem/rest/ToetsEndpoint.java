package com.cs.cijferSysteem.rest;


import com.cs.cijferSysteem.controller.DocentService;
import com.cs.cijferSysteem.controller.ToetsService;
import com.cs.cijferSysteem.controller.VakService;
import com.cs.cijferSysteem.domein.Docent;
import com.cs.cijferSysteem.domein.Leerling;
import com.cs.cijferSysteem.domein.Toets;
import com.cs.cijferSysteem.domein.Vak;
import com.cs.cijferSysteem.dto.CreateLeerlingDto;
import com.cs.cijferSysteem.dto.CreateToetsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
public class ToetsEndpoint {
    @Autowired
    ToetsService ts;

    @Autowired
    VakService vs;

    @Autowired
    DocentService ds;


    @GetMapping("/toetsOverzicht")
    public Iterable<Toets> geefOverzichtLeerling() {
        return ts.laatToetsZien();
    }

    @PostMapping("/api/maakToets")
    public void maakToetsAan(@RequestBody CreateToetsDto createToetsDto) {
        Toets toets = new Toets();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        toets.setDatum(LocalDate.parse(createToetsDto.getDatum(), formatter));
        DateTimeFormatter formatterTijd = DateTimeFormatter.ofPattern("HH:MM");
        toets.setTijd(LocalTime.parse(createToetsDto.getTijd(), formatterTijd));
        
        Docent d = ds.getDocentById(createToetsDto.getDocentId()).get();
        Vak v = vs.getVakById(createToetsDto.getVakId()).get();
   
        toets.setDocent(d);
        toets.setVak(v);
        toets = ts.save(toets);
        
        v.voegToetsToe(toets);
        vs.update(v);
        
        d.voegToetsToe(toets);
        ds.update(d);
    }


    @GetMapping("/toets/{id}")
    public Optional<Toets> getLeerlingById(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        return ts.toonToets(id);
    }
}
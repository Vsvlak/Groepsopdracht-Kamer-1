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
        System.out.println(6);
        Toets toets = new Toets();
        System.out.println(7);
        Docent d = ds.getDocentById(createToetsDto.getDocentId()).get();
        System.out.println(8);
        d.voegToetsToe(toets);
        System.out.println(d.getAchternaam());
        System.out.println(d.getId());
        System.out.println(d.geefToetsen());
        ds.update(d);
        System.out.println(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        toets.setDatum(LocalDate.parse(createToetsDto.getDatum(), formatter));
        DateTimeFormatter formatterTijd = DateTimeFormatter.ofPattern("HH:MM");
        toets.setTijd(LocalTime.parse(createToetsDto.getTijd(), formatterTijd));
        Vak v = vs.getVakById(createToetsDto.getVakId()).get();
        System.out.println(2);
        v.voegToetsToe(toets);
        System.out.println(3);
        vs.update(v);
        System.out.println(4);
        this.ts.save(toets);
        System.out.println(5);

        System.out.println(toets.getDatum());
        //System.out.println(toets.getVakId());
        System.out.println(toets.getTijd());
    }


    @GetMapping("/toets/{id}")
    public Optional<Toets> getLeerlingById(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        return ts.toonToets(id);
    }
}
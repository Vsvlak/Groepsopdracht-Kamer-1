package com.cs.cijferSysteem.rest;

import com.cs.cijferSysteem.controller.DocentService;
import com.cs.cijferSysteem.controller.KlasService;
import com.cs.cijferSysteem.controller.ToetsService;
import com.cs.cijferSysteem.controller.VakService;
import com.cs.cijferSysteem.domein.Docent;
import com.cs.cijferSysteem.domein.Klas;
import com.cs.cijferSysteem.domein.Leerling;
import com.cs.cijferSysteem.domein.LeerlingCijfersVanToetsIds;
import com.cs.cijferSysteem.domein.Toets;
import com.cs.cijferSysteem.domein.Vak;
import com.cs.cijferSysteem.dto.CreateToetsDto;
import com.cs.cijferSysteem.dto.DocentCijferOverzichtDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToetsEndpoint {
    @Autowired
    ToetsService ts;

    @Autowired
    VakService vs;

    @Autowired
    DocentService ds;
    
    @Autowired
    KlasService ks;


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

    @GetMapping("/toets/{leerlingid}")
    public Optional<Toets> getLeerlingById(@PathVariable("leerlingid") Long id) {
        System.out.println("id = " + id);
        return ts.toonToets(id);
    }
    
    @GetMapping("toonToetsenVan/{docentid}/{vakid}")
    public Iterable<Toets> toetsenVanId(@PathVariable("docentid") Long docentid, @ PathVariable("vakid") Long vakid){
    	return ts.toonToetsenVan(docentid, vakid);
    }
    
    @GetMapping("toonToetsenVan/{docentid}/{vakid}/{klasid}")
    public DocentCijferOverzichtDto docentCijferOverzicht(@PathVariable("docentid") Long docentid, @ PathVariable("vakid") Long vakid, @PathVariable("klasid") Long klasid) {
    	DocentCijferOverzichtDto dco = new DocentCijferOverzichtDto();
    	List<LeerlingCijfersVanToetsIds> list = new ArrayList<LeerlingCijfersVanToetsIds>();
    	Klas k = ks.getKlasById(klasid).get();
    	List<Leerling> leerlingen = k.getLeerlingen();
    	Iterable<Toets> toetsen = ts.toonToetsenVan(docentid, vakid);
    	for(Leerling l : leerlingen) {
    		list.add(new LeerlingCijfersVanToetsIds(l, toetsen));
    	}
    	dco.setList(list);
    	System.out.println(dco + " " + dco.getList().size());
    	return dco;
    }    
}
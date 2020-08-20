package com.cs.cijferSysteem.rest;

import com.cs.cijferSysteem.controller.DocentService;
import com.cs.cijferSysteem.controller.DocentVakService;
import com.cs.cijferSysteem.controller.KlasService;
import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.controller.ToetsService;
import com.cs.cijferSysteem.controller.VakService;
import com.cs.cijferSysteem.domein.Docentvak;
import com.cs.cijferSysteem.domein.Klas;
import com.cs.cijferSysteem.domein.Toets;
import com.cs.cijferSysteem.dto.CreateToetsDto;

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
    @Autowired
    LeerlingService ls;
    @Autowired
    DocentVakService dvs;

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
        
        Docentvak dv = dvs.getByDocentIdAndVakId(createToetsDto.getDocentId(), createToetsDto.getVakId());
        toets.setDocentvak(dv);
        Klas k = ks.getKlasById(createToetsDto.getKlasId()).get();
        toets.setKlas(k);
        ts.save(toets);
        
        dv.voegToetsToe(toets);
        k.voegToetsToe(toets);
        dvs.save(dv);
        ks.update(k);
    }

    @GetMapping("/toets/{toetsid}")
    public Optional<Toets> getToetsById(@PathVariable("toetsid") Long toetsid) {
        return ts.toonToets(toetsid);
    }
    
    @GetMapping("toonToetsenVanDocentvak/{docentvakid}")
    public Iterable<Toets> toonToetsenVanDocentVak(@PathVariable("docentvakid") Long docentvakid){
    	return ts.findToetsByDocentvak(docentvakid);
    }
    
    @GetMapping("toonToetsenVanDocent/{docentid}")
    public Iterable<Toets> toonToetsenVanDocent(@PathVariable("docentid") Long docentid){
    	Iterable<Docentvak> docentvakken = dvs.getByDocentId(docentid);
    	List<Toets> toetsen = new ArrayList<>();
    	for(Docentvak dv : docentvakken) {
    		toetsen.addAll(dv.getToetsen());
    	}
    	return toetsen;
    }
    
    @GetMapping("toonToetsenVanVak/{vakid}")
    public Iterable<Toets> toonToetsenVanVak(@PathVariable("vakid") Long vakid){
    	Iterable<Docentvak> docentvakken = dvs.getByVakId(vakid);
    	List<Toets> toetsen = new ArrayList<>();
    	for(Docentvak dv : docentvakken) {
    		toetsen.addAll(dv.getToetsen());
    	}
    	return toetsen;
    }
    
    @GetMapping("toonToetsenVanKlas/{klasid}")
    public Iterable<Toets> toonToetsenVanKlas(@PathVariable("klasid") Long klasid){
    	return ts.findToetsByKlas(klasid);
    }
    
    @GetMapping("toonToetsenVanDocentEnVak/{docentid}/{vakid}")
    public Iterable<Toets> toonToetsenVanDocentEnVak(@PathVariable("docentid") Long docentid, @PathVariable("vakid") Long vakid){
    	return dvs.getByDocentIdAndVakId(docentid, vakid).getToetsen();
    }
    
    @GetMapping("toonToetsenVan/{docentid}/{vakid}/{klasid}")
    public Iterable<Toets> toonToetsenVan(@PathVariable("docentid") Long docentid, @ PathVariable("vakid") Long vakid, @PathVariable("klasid") Long klasid) {
    	Docentvak dv = dvs.getByDocentIdAndVakId(docentid, vakid);
    	return ts.findToetsByDocentvakAndKlas(dv.getId(), klasid);
    }    
}
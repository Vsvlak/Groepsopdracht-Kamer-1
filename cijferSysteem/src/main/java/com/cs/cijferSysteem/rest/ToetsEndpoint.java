package com.cs.cijferSysteem.rest;

import com.cs.cijferSysteem.controller.DocentService;
import com.cs.cijferSysteem.controller.DocentVakService;
import com.cs.cijferSysteem.controller.KlasService;
import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.controller.ToetsService;
import com.cs.cijferSysteem.controller.VakService;
import com.cs.cijferSysteem.domein.Docent;
import com.cs.cijferSysteem.domein.Docentvak;
import com.cs.cijferSysteem.domein.Klas;
import com.cs.cijferSysteem.domein.Leerling;
import com.cs.cijferSysteem.domein.Toets;
import com.cs.cijferSysteem.domein.Vak;
import com.cs.cijferSysteem.dto.CreateToetsDto;
import com.cs.cijferSysteem.dto.LeerlingCijfersVanDocentVakDto;
import com.cs.cijferSysteem.dto.ToetsDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
    public Stream<ToetsDto> geefOverzichtLeerling() {
    	
        return ts.laatToetsZien().stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString(), t.getVak().getId(), t.getDocent().getId(), t.getKlas().getId()));
    }

    @GetMapping("/toets/{toetsid}")
    public Optional<ToetsDto> getToetsById(@PathVariable("toetsid") Long toetsid) {
        return ts.toonToets(toetsid).map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString(), t.getVak().getId(), t.getDocent().getId(), t.getKlas().getId()));
    }
    
    @GetMapping("toonToetsenVanDocentvak/{docentvakid}")
    public Stream<ToetsDto> toonToetsenVanDocentVak(@PathVariable("docentvakid") Long docentvakid){
    	return ts.findToetsByDocentvak(docentvakid).stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString(), t.getVak().getId(), t.getDocent().getId(), t.getKlas().getId()));
    }
    
    @GetMapping("toonToetsenVanDocent/{docentid}")
    public Stream<ToetsDto> toonToetsenVanDocent(@PathVariable("docentid") Long docentid){
    	Optional <Docent> docentOptional = ds.toonDocentById(docentid);
    	Iterable<Docentvak> docentvakken = dvs.getByDocent(docentOptional.get());
    	List<Toets> toetsen = new ArrayList<>();
    	for(Docentvak dv : docentvakken) {
    		toetsen.addAll(dv.getToetsen());
    	}
    	return toetsen.stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString(), t.getVak().getId(), t.getDocent().getId(), t.getKlas().getId()));
    }
    
    @GetMapping("toonToetsenVanVak/{vakid}")
    public Stream<ToetsDto> toonToetsenVanVak(@PathVariable("vakid") Long vakid){
    	
    	Optional <Vak> vakOptional = vs.toonVakById(vakid);
    	Iterable<Docentvak> docentvakken = dvs.getByVak(vakOptional.get());
    	List<Toets> toetsen = new ArrayList<>();
    	for(Docentvak dv : docentvakken) {
    		toetsen.addAll(dv.getToetsen());
    	}
    	return toetsen.stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString(), t.getVak().getId(), t.getDocent().getId(), t.getKlas().getId()));
    }
    
    @GetMapping("toonToetsenVanKlas/{klasid}")
    public Stream<ToetsDto> toonToetsenVanKlas(@PathVariable("klasid") Long klasid){
    	return ts.findToetsByKlas(klasid).stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString(), t.getVak().getId(), t.getDocent().getId(), t.getKlas().getId()));
    }
    
    @GetMapping("toonToetsenVanDocentEnVak/{docentid}/{vakid}")
    public Stream<ToetsDto> toonToetsenVanDocentEnVak(@PathVariable("docentid") Long docentid, @PathVariable("vakid") Long vakid){
    	
    	Optional <Docent> docentOptional = ds.toonDocentById(docentid);
    	Optional <Vak> vakOptional = vs.toonVakById(vakid);
    	
    	Docentvak dv = dvs.getByDocentAndVak(docentOptional.get(), vakOptional.get());
    	
    	return dv.getToetsen().stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString(), t.getVak().getId(), t.getDocent().getId(), t.getKlas().getId()));
    }
    //TODO
    @GetMapping("toonToetsenVan/{docentid}/{vakid}/{klasid}")
    public Stream<ToetsDto> toonToetsenVan(@PathVariable("docentid") Long docentid, @ PathVariable("vakid") Long vakid, @PathVariable("klasid") Long klasid) {
    	Optional <Docent> docentOptional = ds.toonDocentById(docentid);
    	Optional <Vak> vakOptional = vs.toonVakById(vakid);
    	
    	Docentvak dv = dvs.getByDocentAndVak(docentOptional.get(), vakOptional.get());
    	
    	return ts.findToetsByDocentvakAndKlas(dv.getId(), klasid).stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString(), t.getVak().getId(), t.getDocent().getId(), t.getKlas().getId()));
    }
    
    @PostMapping("/api/maakToets")
    public void maakToetsAan(@RequestBody CreateToetsDto createToetsDto) {
    	
    	Klas k = ks.getKlasById(createToetsDto.getKlasId()).get();
    	Optional <Docent> docentOptional = ds.toonDocentById(createToetsDto.getDocentId());
    	Optional <Vak> vakOptional = vs.toonVakById(createToetsDto.getVakId());
    	
    	Docentvak dv = dvs.getByDocentAndVak(docentOptional.get(), vakOptional.get());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterTijd = DateTimeFormatter.ofPattern("HH:MM");
        
    	Toets toets = new Toets();
        toets.setDatum(LocalDate.parse(createToetsDto.getDatum(), formatter));
        toets.setTijd(LocalTime.parse(createToetsDto.getTijd(), formatterTijd));
        toets.setDocentvak(dv);
        toets.setKlas(k);
        ts.save(toets);
 
       
    } 
}


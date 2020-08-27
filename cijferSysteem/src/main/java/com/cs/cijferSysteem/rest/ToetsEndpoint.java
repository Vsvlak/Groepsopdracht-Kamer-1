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
        return ts.laatToetsZien().stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString(), t.getVak().getNaam(), t.getDocent().getAchternaam(), t.getKlas().getNaam()));
    }

    @GetMapping("/toets/{toetsid}")
    public Optional<ToetsDto> getToetsById(@PathVariable("toetsid") Long toetsid) {
        return ts.toonToets(toetsid).map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString()));
    }
    
    @GetMapping("toonToetsenVanDocentvak/{docentvakid}")
    public Stream<ToetsDto> toonToetsenVanDocentVak(@PathVariable("docentvakid") Long docentvakid){
    	return ts.findToetsByDocentvak(docentvakid).stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString()));
    }
    
    @GetMapping("toonToetsenVanDocent/{docentid}")
    public Stream<ToetsDto> toonToetsenVanDocent(@PathVariable("docentid") Long docentid){
    	Iterable<Docentvak> docentvakken = dvs.getByDocentId(docentid);
    	List<Toets> toetsen = new ArrayList<>();
    	for(Docentvak dv : docentvakken) {
    		toetsen.addAll(dv.getToetsen());
    	}
    	return toetsen.stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString()));
    }
    
    @GetMapping("toonToetsenVanVak/{vakid}")
    public Stream<ToetsDto> toonToetsenVanVak(@PathVariable("vakid") Long vakid){
    	Iterable<Docentvak> docentvakken = dvs.getByVakId(vakid);
    	List<Toets> toetsen = new ArrayList<>();
    	for(Docentvak dv : docentvakken) {
    		toetsen.addAll(dv.getToetsen());
    	}
    	return toetsen.stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString(), t.getVak().getNaam(), t.getDocent().getAchternaam(), t.getKlas().getNaam()));
    }
    
    @GetMapping("toonToetsenVanKlas/{klasid}")
    public Stream<ToetsDto> toonToetsenVanKlas(@PathVariable("klasid") Long klasid){
    	return ts.findToetsByKlas(klasid).stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString(), t.getVak().getNaam(), t.getDocent().getAchternaam(), t.getKlas().getNaam()));
    }
    
    @GetMapping("toonToetsenVanDocentEnVak/{docentid}/{vakid}")
    public Stream<ToetsDto> toonToetsenVanDocentEnVak(@PathVariable("docentid") Long docentid, @PathVariable("vakid") Long vakid){
    	return dvs.getByDocentIdAndVakId(docentid, vakid).getToetsen().stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString()));
    }
    
    @GetMapping("toonToetsenVan/{docentid}/{vakid}/{klasid}")
    public Stream<ToetsDto> toonToetsenVan(@PathVariable("docentid") Long docentid, @ PathVariable("vakid") Long vakid, @PathVariable("klasid") Long klasid) {
    	Docentvak dv = dvs.getByDocentIdAndVakId(docentid, vakid);
    	return ts.findToetsByDocentvakAndKlas(dv.getId(), klasid).stream().map(t -> new ToetsDto(t.getId(), t.getDatum().toString(), t.getTijd().toString()));
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
}
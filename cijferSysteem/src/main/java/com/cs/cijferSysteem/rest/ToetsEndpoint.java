package com.cs.cijferSysteem.rest;

import com.cs.cijferSysteem.controller.DocentService;
import com.cs.cijferSysteem.controller.DocentVakService;
import com.cs.cijferSysteem.controller.KlasService;
import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.controller.ToetsService;
import com.cs.cijferSysteem.controller.VakService;
import com.cs.cijferSysteem.domein.Cijfer;
import com.cs.cijferSysteem.domein.DocentVak;
import com.cs.cijferSysteem.domein.Leerling;
import com.cs.cijferSysteem.domein.Toets;
import com.cs.cijferSysteem.dto.CreateToetsDto;
import com.cs.cijferSysteem.dto.LeerlingCijfersVanDocentVakDto;

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
    	//TODO: Voeg klas toe bij aanmaken toets
        Toets toets = new Toets();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        toets.setDatum(LocalDate.parse(createToetsDto.getDatum(), formatter));
        DateTimeFormatter formatterTijd = DateTimeFormatter.ofPattern("HH:MM");
        toets.setTijd(LocalTime.parse(createToetsDto.getTijd(), formatterTijd));
        
        DocentVak dv = dvs.getByDocentIdAndVakId(createToetsDto.getDocentId(), createToetsDto.getVakId());
        toets.setDocentvak(dv);
        toets = ts.save(toets);
        
        dv.voegToetsToe(toets);
        dvs.save(dv);
    }

    @GetMapping("/toets/{toetsid}")
    public Optional<Toets> getToetsById(@PathVariable("toetsid") Long toetsid) {
        return ts.toonToets(toetsid);
    }
    
    @GetMapping("toonToetsenVan/{docentid}/{vakid}")
    public Iterable<Toets> toetsenVanId(@PathVariable("docentid") Long docentid, @ PathVariable("vakid") Long vakid){
    	return ts.toonToetsenVan(docentid, vakid);
    }
    
	//TODO: toetsenVanDocent
	//TODO: toetsenVanVak
    
    @GetMapping("toonToetsenVan/{docentid}/{vakid}/{klasid}")
    //TODO: Kan deze methode makkelijker nu er een koppeling is tussen Toets en Klas?
    public List<LeerlingCijfersVanDocentVakDto> leeringCijfersVanDocentVak(@PathVariable("docentid") Long docentid, @ PathVariable("vakid") Long vakid, @PathVariable("klasid") Long klasid) {
    	List<Leerling> leerlingen = ks.getKlasById(klasid).get().getLeerlingen();
    	Iterable<Toets> toetsen = ts.toonToetsenVan(docentid, vakid);
    	
    	//Deze lijst wordt returned
    	List<LeerlingCijfersVanDocentVakDto> list = new ArrayList<>();

    	for(Leerling l : leerlingen) {
    		//In lijst 'cijfers' worden de cijfers van leerling l voor 'toetsen' opgeslagen
    		List<Float> cijfers = new ArrayList<>();
    		for(Toets t:  toetsen) {
        		//Haal alle cijfers op van deze toets
    			List<Cijfer> toetscijfers = t.getCijfers();
    			boolean gevonden = false;
    			for(Cijfer c : toetscijfers) {
    				//Zoek welk cijfer van de huidige leerling l is
    				if(c.getLeerling().getId() == l.getId()) {
    					cijfers.add(c.getCijfer());
    					gevonden = true;
    					break;
    				}
    			}
    			if(!gevonden) {
    				//Registreer voor elke toets wel een cijfer, ter voorkoming van verschuiving
    				cijfers.add(null);
    			}
    		}
    		LeerlingCijfersVanDocentVakDto dto = new LeerlingCijfersVanDocentVakDto();
        	dto.setCijfers(cijfers);
        	dto.setLeerlingnaam(l.getVoornaam() + " " + l.getAchternaam());
        	list.add(dto);
    	}    	
    	return list;
    }    
}
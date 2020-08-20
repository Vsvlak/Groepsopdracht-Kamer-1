package com.cs.cijferSysteem.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.DocentService;
import com.cs.cijferSysteem.controller.DocentVakService;
import com.cs.cijferSysteem.controller.VakService;
import com.cs.cijferSysteem.domein.Docent;
import com.cs.cijferSysteem.domein.DocentVak;
import com.cs.cijferSysteem.domein.Klas;
import com.cs.cijferSysteem.domein.Vak;
import com.cs.cijferSysteem.dto.DocentVakDto;
import com.cs.cijferSysteem.dto.KlasDto;

@RestController
public class DocentVakEndpoint {
	@Autowired
	DocentVakService dvs;
	@Autowired
	DocentService ds;
	@Autowired
	VakService vs;
	
	@GetMapping("/docentVakOverzicht")
	public Iterable<DocentVak> geefDocentVakOverzicht(){
		return dvs.laatDocentVakkenZien();
	}
	
	@PostMapping("/maakDocentVak")
	public void maakDocentVak(@RequestBody DocentVakDto dto) {
		DocentVak dv = new DocentVak();
		Docent d = ds.getDocentById(dto.getDocentid()).get();
		Vak v = vs.getVakById(dto.getVakid()).get();
		dv.setDocent(d);
		dv.setVak(v);
		dvs.save(dv);
		
		d.voegDocentVakToe(dv);
		ds.update(d);
		v.voegDocentVakToe(dv);
		vs.update(v);
	}
	
	@GetMapping("/klassenVanDocentEnVak/{docentid}/{vakid}")
	public List<KlasDto> geefKlassenVanDocentVak(@PathVariable("docentid") Long docentid, @PathVariable("vakid") Long vakid){
		System.out.println("docent " + docentid + " vak " + vakid);
		List<Klas> klassen = dvs.getByDocentIdAndVakId(docentid, vakid).getKlassen();
		System.out.println(klassen.size());
		List<KlasDto> klassendtos = new ArrayList<>();
		for(Klas k : klassen) {
			KlasDto dto = new KlasDto();
			dto.setId(k.getId());
			dto.setNaam(k.getNaam());
			dto.setNiveau(k.getNiveau());
			klassendtos.add(dto);
		}
		return klassendtos;
	}
	
	//TODO: klassenVanDocentEnVak
	@GetMapping("/klassenVanDocentVak/{docentvakid}}")
	public List<KlasDto> geefKlassenVanDocentVak(@PathVariable("docentvakid") Long docentvakid){
		List<Klas> klassen = dvs.getDocentVakById(docentvakid).get().getKlassen();
		List<KlasDto> klassendtos = new ArrayList<>();
		for(Klas k : klassen) {
			KlasDto dto = new KlasDto();
			dto.setId(k.getId());
			dto.setNaam(k.getNaam());
			dto.setNiveau(k.getNiveau());
			klassendtos.add(dto);
		}
		return klassendtos;
	}
	
}

package com.cs.cijferSysteem.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.DocentService;

import com.cs.cijferSysteem.controller.VakService;
import com.cs.cijferSysteem.domein.Docent;

import com.cs.cijferSysteem.domein.DocentVak;

import com.cs.cijferSysteem.domein.Vak;
import com.cs.cijferSysteem.dto.DocentDto;
import com.cs.cijferSysteem.dto.DocentVakkenDto;
import com.cs.cijferSysteem.dto.VakDto;


@RestController
public class DocentEndpoint {
	@Autowired
	DocentService ds;
	@Autowired
	VakService vs;


	@GetMapping("/docentOverzicht")
	public List<DocentDto> geefOverzichtDocent() {
		Iterable<Docent> docenten = ds.laatDocentZien();
		List<DocentDto> docentdtos = new ArrayList<>();
		for(Docent d : docenten) {
			DocentDto dto = new DocentDto();
			dto.setAchternaam(d.getAchternaam());
			dto.setId(d.getId());
			dto.setVoornaam(d.getVoornaam());
			docentdtos.add(dto);
		}
		return docentdtos;
	}
	
	@PostMapping("api/maakDocent")
	public void maakDocent(@RequestBody Docent docent) { 
		ds.maakDocent(docent);
	}
	

	@PostMapping("api/editDocent/{id}")
	public void updateDocent(@RequestBody Docent docent) { 
		ds.update(docent);
	}
	
	@PostMapping("/api/maakDocentAanVak")
	public void maakDocent(@RequestBody DocentVakkenDto docentVakkenDto) { 
		Docent d = ds.toonDocentById(docentVakkenDto.getDocentid()).get();
		Vak v = vs.toonVakById(docentVakkenDto.getVakid()).get();
		d.getVakken().add(v);
		ds.update(d);
	} 


	@PostMapping("/api/voegDocentVakToeAanDocent/{docentid}")
	public void voegDocentVakToeAanDocent(@PathVariable("docentid") Long docentid, @RequestBody DocentVak dv) {
		ds.toonDocentById(docentid).get().voegDocentVakToe(dv);

	}
	
	@GetMapping("/docent/{id}")
	public Optional<Docent> getDocentById(@PathVariable("id")Long id){ 
		return ds.toonDocentById(id);
	}
	
	@GetMapping("/vakkenVanDocent/{docentid}")
	public List<VakDto> getVakvanDocent(@PathVariable("docentid")Long docentid){ 
		List<Vak> vakken = ds.getDocentById(docentid).get().getVakken();
		List<VakDto> vakdtos = new ArrayList<>();
		for(Vak v : vakken) {
			VakDto dto = new VakDto();
			dto.setId(v.getId());
			dto.setNaam(v.getNaam());
			vakdtos.add(dto);
		}
		return vakdtos;
	}
	
	@GetMapping("/docentenVanVak/{vakid}")
	public List<DocentDto> getDocentenVanVak(@PathVariable("vakid") Long vakid){ 
		
		List<Docent> docenten = vs.getVakById(vakid).get().getDocenten();
		List<DocentDto> docentdtos = new ArrayList<>();
		for(Docent d : docenten) {
			DocentDto dto = new DocentDto();
			dto.setId(d.getId());
			dto.setAchternaam(d.getAchternaam());
			dto.setVoornaam(d.getVoornaam());
			docentdtos.add(dto);
		}
		return docentdtos;
	}
	
	
//	@GetMapping("/langsteAchternaamDocent")
//	public String toonLangsteAchternaamDocent() { 
//		List <Docent> docenten = ds.laatDocentZien();
//		Stream <Docent> docentenStream  = docenten.stream().filter(d-> d.getAchternaam().contains("J"));
//		Optional <String> langsteAchternaam = docentenStream.map(n -> n.getAchternaam()).max((l1,l2)-> new Integer(l1.length()).compareTo(l2.length()));
//		return langsteAchternaam.get();
//	}
	
}


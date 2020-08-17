package com.cs.cijferSysteem.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.DocentVakService;
import com.cs.cijferSysteem.controller.KlasService;
import com.cs.cijferSysteem.controller.LeerlingService;
import com.cs.cijferSysteem.controller.VakService;

import com.cs.cijferSysteem.domein.Docent;

import com.cs.cijferSysteem.domein.DocentVak;

import com.cs.cijferSysteem.domein.Klas;
import com.cs.cijferSysteem.domein.Leerling;
import com.cs.cijferSysteem.domein.Vak;
import com.cs.cijferSysteem.dto.DocentVakDto;
import com.cs.cijferSysteem.dto.KlasDto;
import com.cs.cijferSysteem.dto.KlasLeerlingDto;
import com.cs.cijferSysteem.dto.KlasVakDto;
import com.cs.cijferSysteem.dto.VakDto;

@RestController
public class KlasEndpoint {

	@Autowired
	KlasService ks;
	@Autowired
	LeerlingService ls;	
	@Autowired
	VakService vs;	
	@Autowired
	DocentVakService dvs;	
	
	@GetMapping("/klassenOverzicht")
	public List<KlasDto> toonKlassenOverzicht(){
		Iterable<Klas> klassen = ks.laatKlasZien();
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
	

	// hieronder check
	@GetMapping("/klassen/{id}")
	public Optional<Klas> getKlasById(@PathVariable("id")Long id){ 
		return ks.getKlasById(id);
	}

	@GetMapping("/leerlingenInKlas/{klasid}")
	public List<Leerling> toonLeerlingenVanKlas(@PathVariable("klasid") Long id){
		return ks.getKlasById(id).get().getLeerlingen();
	}
	
	@GetMapping("/vakkenVanKlas/{id}")
	public List<DocentVakDto> toonVakkenVanKlas(@PathVariable("id") Long id){
		List<DocentVakDto> docentvakdtos = new ArrayList<>();
		Iterable<DocentVak> docentvakken = ks.getKlasById(id).get().getDocentvakken();
		for(DocentVak v : docentvakken) {
			DocentVakDto dto = new DocentVakDto();
			dto.setId(v.getId());
			dto.setDocentAchternaam(v.getDocent().getAchternaam());
			dto.setDocentid(v.getDocent().getId());
			dto.setVakid(v.getVak().getId());
			dto.setVaknaam(v.getVak().getNaam());
			docentvakdtos.add(dto);
		}
		return docentvakdtos;
	}
	
	@PostMapping("/api/maakKlas")
	public void maakVak(@RequestBody Klas k) {
		ks.update(k);
	}
	
	@PostMapping("api/editKlas/{id}")
	public void updateKlas(@RequestBody Klas klas) { 
		ks.update(klas);
	}
	
	@PostMapping("/api/voegLeerlingToe")
	public void voegLeerlingToe(@RequestBody KlasLeerlingDto klasLeerlingDto) {
		Klas k = ks.getKlasById(klasLeerlingDto.getKlasid()).get();
		Leerling l = ls.toonLeerling(klasLeerlingDto.getLeerlingid()).get();
		if(!k.getLeerlingen().contains(l)) {
			k.getLeerlingen().add(l);
			ks.update(k);
		}
		//Moet de klas niet ook aan de leerling worden toegevoegd, #dubbelzichtige relaties
	}
	
	@PostMapping("/api/voegDocentVakToe/{klasid}")
	public void voegDocentVakToe(@PathVariable("klasid") Long klasid, @RequestBody DocentVakDto dto) {
		DocentVak dv = dvs.getByDocentIdAndVakId(dto.getDocentid(), dto.getVakid());
		Klas k = ks.getKlasById(klasid).get();
		if(!k.getDocentvakken().contains(dv)) {
			k.voegDocentVakToe(dv);
			dv.voegKlasToe(k);
			ks.update(k);
			dvs.save(dv);
		}
	}
}

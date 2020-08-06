package com.cs.cijferSysteem.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.cijferSysteem.controller.KlasService;
import com.cs.cijferSysteem.domein.Klas;
import com.cs.cijferSysteem.domein.Leerling;

@RestController
public class KlasEndpoint {

	@Autowired
	KlasService ks;
	
	@GetMapping("/klassenOverzicht")
	public Iterable<Klas> toonKlassenOverzicht(){
		return ks.laatKlassenZien();
	}
	
	@GetMapping("/leerlingenInKlas/{id}")
	public List<Leerling> toonLeerlingenVanKlas(@PathVariable("id") Long id){
		return ks.getKlasById(id).get().getLeerlingen();
	}
	
	@PostMapping("/api/maakKlas")
	public void maakVak(@RequestBody Klas k) {
		ks.maakKlas(k);
	}
	
//	@PostMapping("/api/voegLeerlingToe/{klasid}/{leerlingid}")
//	public void voegLeerlingToe(@PathVariable("klasid") Klas klasid, @PathVariable("leerlingid") Long leerlingid) {
//		ks.getKlasById(klasid).get().voegLeerlingToe(leerlingid);
//	}
}

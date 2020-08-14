package com.cs.cijferSysteem.domein;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Docent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String achternaam;
	private String voornaam;
//	@ManyToMany
//	@JsonIgnore
//	private List<Vak> vakken;
	@OneToMany
	private List<Toets> toetsen;
	@OneToMany
	private List<DocentVak> docentvakken;


	public void voegToetsToe(Toets t){
		toetsen.add(t);
	}
	
	public void voegDocentVakToe(DocentVak dv) {
		docentvakken.add(dv);
	}

//	public void voegVakToe(Vak v) { 
//		if(!vakken.contains(v)) {
//			vakken.add(v);
//		}
//	}
//
	public List<Vak> getVakken() {
		List<Vak> vakken = new ArrayList<>();
		for(DocentVak dv : docentvakken) {
			vakken.add(dv.getVak());
		}
		return vakken;
	}

	public List<Toets> getToetsen() {
		return toetsen;
	}

	public void setToetsen(List<Toets> toetsen) {
		this.toetsen = toetsen;
	}

	public List<DocentVak> getDocentvakken() {
		return docentvakken;
	}

	public void setDocentvakken(List<DocentVak> docentvakken) {
		this.docentvakken = docentvakken;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

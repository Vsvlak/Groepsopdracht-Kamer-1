package com.cs.cijferSysteem.domein;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Klas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String naam;
	private String niveau;

	@ManyToMany
	@JsonIgnore
	private List<Leerling> leerlingen;

	@ManyToMany(mappedBy = "klassen", cascade = CascadeType.ALL)
	private List<DocentVak> docentvakken;

	
	public void voegDocentVakToe(DocentVak dv) {
		docentvakken.add(dv);
	}
	
	public List<DocentVak> getDocentvakken() {
		return docentvakken;
	}

	public void setDocentvakken(List<DocentVak> docentvakken) {
		this.docentvakken = docentvakken;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLeerlingen(List<Leerling> leerlingen) {
		this.leerlingen = leerlingen;
	}

	public void voegLeerlingToe(Leerling l) {
		leerlingen.add(l);
	}

//	public void voegVakToe(Vak v) {
//		vakkenpakket.add(v);
//	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public List<Leerling> getLeerlingen() {
		return leerlingen;
	}

	public List<Vak> getVakken(){
		List<Vak> vakken = new ArrayList<>();
		for(DocentVak dv : docentvakken) {
			vakken.add(dv.getVak());
		}
		return vakken;
	}
}

package com.cs.cijferSysteem.domein;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Docent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String achternaam;
	private String voornaam;

	@ManyToMany
	@JsonIgnore
	private List<Vak> vakken;

	public void voegVakToe(Vak v) { 
		if(!vakken.contains(v)) {
			vakken.add(v);
		}
	}

	public List<Vak> getVakken() {
		return vakken;
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

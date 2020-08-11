package com.cs.cijferSysteem.domein;

import java.util.List;

import javax.persistence.*;

@Entity
public class Docent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String achternaam;
	private String voornaam;
	@ManyToMany
	private List<Vak> vakken;
	@OneToMany
	private List<Toets> toetsen;


	public List<Toets> geefToetsen(){
		return toetsen;
	}


	public void voegToetsToe(Toets t){
		toetsen.add(t);
	}

	public List<Vak> geefVakken() {
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

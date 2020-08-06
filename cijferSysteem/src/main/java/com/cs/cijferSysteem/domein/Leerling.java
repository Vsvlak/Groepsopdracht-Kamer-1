package com.cs.cijferSysteem.domein;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Leerling {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)


	private Long id;
	private int leerlingNummer;
	private String voornaam;
	private String achternaam;
	private LocalDate geboorteDatum;

	
	@ManyToMany
	private List<Klas> klassen;



	public LocalDate getGeboorteDatum() {
		return geboorteDatum;
	}
	public void setGeboorteDatum(LocalDate geboorteDatum) {
		this.geboorteDatum = geboorteDatum;
	}
	

	public long getLeerlingNummer() {
		return leerlingNummer;
	}

	public void setLeerlingNummer(int leerlingNummer) {
		this.leerlingNummer = leerlingNummer;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
}


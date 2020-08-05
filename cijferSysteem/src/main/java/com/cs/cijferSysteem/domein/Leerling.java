package com.cs.cijferSysteem.domein;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Leerling {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
<<<<<<< HEAD

	long leerlingNummer;
	String voornaam;
	String achternaam;
	LocalDateTime geboorteDatum;
	






=======
	private long id;
	private int leerlingNummer;
	private String naam;
	private LocalDateTime geboorteDatum;
>>>>>>> master

	public LocalDateTime getGeboorteDatum() {
		return geboorteDatum;
	}
	public void setGeboorteDatum(LocalDateTime geboorteDatum) {
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


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
	
	long id;
	int leerlingnummer;
	String naam;
	LocalDateTime geboorteDatum;
	
	
	public LocalDateTime getGeboorteDatum() {
		return geboorteDatum;
	}
	public void setGeboorteDatum(LocalDateTime geboorteDatum) {
		this.geboorteDatum = geboorteDatum;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getLeerlingnummer() {
		return leerlingnummer;
	}
	public void setLeerlingnummer(int leerlingnummer) {
		this.leerlingnummer = leerlingnummer;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}

	
	
}


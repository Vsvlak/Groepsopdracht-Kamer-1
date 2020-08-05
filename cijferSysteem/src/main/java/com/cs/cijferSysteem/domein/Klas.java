package com.cs.cijferSysteem.domein;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Klas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String naam;
	private String niveau;
	private ArrayList<Leerling> leerlingen;
	//private ArrayList<Vak>

	public void voegLeerlingToe(Leerling l) {
		leerlingen.add(l);
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
}

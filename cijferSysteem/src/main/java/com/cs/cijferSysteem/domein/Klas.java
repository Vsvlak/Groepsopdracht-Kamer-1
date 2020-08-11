package com.cs.cijferSysteem.domein;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Klas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String naam;
	private String niveau;

	@ManyToMany
	private List<Leerling> leerlingen;

	@ManyToMany
	private List<Vak> vakkenpakket;

	public void voegLeerlingToe(Leerling l) {
		leerlingen.add(l);
	}

	public void voegVakToe(Vak v) {
		vakkenpakket.add(v);
	}



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

	public List<Leerling> geefLeerlingen() {
		return leerlingen;
	}

	public List<Vak> geefVakken(){
		return vakkenpakket;
	}
}

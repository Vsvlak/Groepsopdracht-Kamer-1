package com.cs.cijferSysteem.domein;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Klas {
	//Wil eigenlijk id er uit slopen en naam als ID opslaan.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String naam;
	private String niveau;

	@ManyToMany(mappedBy = "klassen")
	private List<Leerling> leerlingen;

	public void voegLeerlingToe(Leerling l) {
		leerlingen.add(l);
	}
	
	public long getId() {
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
}

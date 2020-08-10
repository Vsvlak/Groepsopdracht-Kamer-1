package com.cs.cijferSysteem.domein;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Vak {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String naam;

	@ManyToMany(mappedBy = "vakken", cascade = {CascadeType.ALL})
	private List<Docent> docenten;

	@ManyToMany(mappedBy = "vakkenpakket", cascade = {CascadeType.ALL})
	private List<Klas> klassen;
	
	@OneToMany
	private List<Toets> toetsen;



	public List<Toets> getToetsen() {
		return toetsen;
	}

	public List<Docent> getDocenten(Docent d) {
		return docenten;
	}

	public void setDocenten(List<Docent> docenten) {
		this.docenten = docenten;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}	
}

package com.cs.cijferSysteem.domein;

import java.util.List;
import javax.persistence.*;

@Entity
public class Vak {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String naam;

	@OneToMany//(cascade = {CascadeType.ALL})
	private List <Toets> toetsen;

	@ManyToMany(mappedBy = "vakken", cascade = {CascadeType.ALL})
	private List<Docent> docenten;

	@ManyToMany(mappedBy = "vakkenpakket", cascade = {CascadeType.ALL})
	private List<Klas> klassen;
	


	public void voegToetsToe(Toets t){
		toetsen.add(t);
	}



	public List<Docent> getDocenten(Docent d) {
		return docenten;
	}
	public void setDocenten(List<Docent> docenten) {
		this.docenten = docenten;
	}

	public List<Toets> getToetsen() {
		return toetsen;
	}

	public void setToetsen(List<Toets> toetsen) {
		this.toetsen = toetsen;
	}

	public List<Docent> getDocenten() {
		return docenten;
	}

	public List<Klas> getKlassen() {
		return klassen;
	}

	public void setKlassen(List<Klas> klassen) {
		this.klassen = klassen;
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

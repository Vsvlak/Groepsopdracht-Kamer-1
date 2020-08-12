package com.cs.cijferSysteem.domein;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vak {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String naam;

	@ManyToMany(mappedBy = "vakken", cascade = { CascadeType.ALL })
	@JsonIgnore
	private List<Docent> docenten;

	@ManyToMany(mappedBy = "vakkenpakket", cascade = { CascadeType.ALL })
	@JsonIgnore
	private List<Klas> klassen;

	@OneToMany
	@JsonIgnore
	private List<Toets> toetsen;

	public void voegToetsToe(Toets t){
		toetsen.add(t);
	}

	public List<Toets> getToetsen() {
		return toetsen;
	}

	public List<Docent> getDocenten() {
		return docenten;
	}

	public List<Klas> getKlassen() {
		return klassen;
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

	@Override
	public boolean equals(Object obj) {
		return obj != null && ((Vak) obj).naam.equals(this.naam);
//		if (obj == null) {
//			return false;
//		}
//		if (obj instanceof Vak) {
//			return ((Vak) obj).getNaam().equals(this.naam);
//
//		} else {
//			return false;
//		}
	}

}

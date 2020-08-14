package com.cs.cijferSysteem.domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DocentVak {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JsonIgnore
	private Vak vak;
	@ManyToOne
	@JsonIgnore
	private Docent docent;
	
	@ManyToMany
	@JsonIgnore
	private List<Klas> klassen;
	
	public List<Klas> getKlas() {
		return klassen;
	}
	public void setKlas(List<Klas> klassen) {
		this.klassen = klassen;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Vak getVak() {
		return vak;
	}
	public void setVak(Vak vak) {
		this.vak = vak;
	}
	public Docent getDocent() {
		return docent;
	}
	public void setDocent(Docent docent) {
		this.docent = docent;
	}	
}

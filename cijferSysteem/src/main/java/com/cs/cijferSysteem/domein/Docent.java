package com.cs.cijferSysteem.domein;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Docent {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		
		private Long id;
		private String achternaam;
		private String voornaam;
		
		//@ManyToMany(mappedBy = "docenten", cascade = {CascadeType.ALL })
		@ManyToMany()
		private List<Vak> vakken;

		
		public List<Vak> getVakken() {
			return vakken;
		}
//		public void setVakken(List<Vak> vakken) {
//			this.vakken = vakken;
//		}
		
		public void voegVakToe(Vak v) { 
			vakken.add(v);
		}
		
		public String getAchternaam() {
			return achternaam;
		}
		public void setAchternaam(String achternaam) {
			this.achternaam = achternaam;
		}
		public String getVoornaam() {
			return voornaam;
		}
		public void setVoornaam(String voornaam) {
			this.voornaam = voornaam;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}

	
}

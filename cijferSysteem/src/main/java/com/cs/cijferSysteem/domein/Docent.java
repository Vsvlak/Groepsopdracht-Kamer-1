package com.cs.cijferSysteem.domein;


import java.util.List;

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
		
		@ManyToMany(mappedBy = "vakken")
		private List<Vak> vakken;

		
		public List<Vak> getVakken(Vak vak) {
			return vakken;
		}
		public void setVakken(List<Vak> vakken) {
			this.vakken = vakken;
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
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}

	
}

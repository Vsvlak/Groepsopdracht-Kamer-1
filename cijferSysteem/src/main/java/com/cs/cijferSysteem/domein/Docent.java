package com.cs.cijferSysteem.domein;


import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Docent {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		
		long id;
		String achternaam;
		String voornaam;
		private ArrayList<Vak> vakken;


		public void voegLeerlingToe(Vak v) {
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
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}

	
}

package com.cs.cijferSysteem.dto;

public class ToetsDto {
	private Long id;
	private String datum;
	private String tijd;
	private String vak;
	private String docent;
	private String klas;
	
	public ToetsDto() {}
	
	public ToetsDto(Long id, String datum, String tijd) {
		super();
		this.id = id;
		this.datum = datum;
		this.tijd = tijd;
	}
	
	public ToetsDto(Long id, String datum, String tijd, String vak, String docent, String klas) {
		super();
		this.id = id;
		this.datum = datum;
		this.tijd = tijd;
		this.vak = vak;
		this.docent = docent;
		this.klas = klas;
	}

	public String getKlas() {
		return klas;
	}

	public void setKlas(String klas) {
		this.klas = klas;
	}

	public String getVak() {
		return vak;
	}

	public void setVak(String vak) {
		this.vak = vak;
	}

	public String getDocent() {
		return docent;
	}

	public void setDocent(String docent) {
		this.docent = docent;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getTijd() {
		return tijd;
	}
	public void setTijd(String tijd) {
		this.tijd = tijd;
	}		
}

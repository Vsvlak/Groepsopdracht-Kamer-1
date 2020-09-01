package com.cs.cijferSysteem.dto;

public class ToetsDto {
	private Long id;
	private String datum;
	private String tijd;
//	private String vak;
//	private String docent;
//	private String klas;
	private Long vakid;
	private Long docentid;
	private Long klasid;
	

	public ToetsDto() { 
		
	}
	
	public ToetsDto(Long id, String datum, String tijd, Long vakid, Long docentid, Long klasid) {
		super();
		this.id = id;
		this.datum = datum;
		this.tijd = tijd;
		this.vakid = vakid;
		this.docentid = docentid;
		this.klasid = klasid;
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
	public Long getVakid() {
		return vakid;
	}
	public void setVakid(Long vakid) {
		this.vakid = vakid;
	}
	public Long getDocentid() {
		return docentid;
	}
	public void setDocentid(Long docentid) {
		this.docentid = docentid;
	}
	public Long getKlasid() {
		return klasid;
	}
	public void setKlasid(Long klasid) {
		this.klasid = klasid;
	}
	
	
}

package com.cs.cijferSysteem.dto;

import java.util.List;

public class LeerlingCijfersVanDocentVakDto {
	String leerlingnaam;
	List<Float> cijfers;
	
	public String getLeerlingnaam() {
		return leerlingnaam;
	}
	public void setLeerlingnaam(String leerlingnaam) {
		this.leerlingnaam = leerlingnaam;
	}
	public List<Float> getCijfers() {
		return cijfers;
	}
	public void setCijfers(List<Float> cijfers) {
		this.cijfers = cijfers;
	}
}

package com.cs.cijferSysteem.domein;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Toets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /*@ManyToOne
    private Docent docent;*/
    private LocalDate datum;
    private LocalTime tijd;

    @OneToMany
	@JsonIgnore
	private List<Cijfer> cijfers;
    
    @ManyToOne
    @JsonIgnore
    private Vak vak;
    @ManyToOne
    @JsonIgnore
    private Docent docent;

    public void voegCijferToe(Cijfer tc) {
    	cijfers.add(tc);
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

	public void setCijfers(List<Cijfer> cijfers) {
		this.cijfers = cijfers;
	}

	public List<Cijfer> getCijfers() {
		return cijfers;
	}

	public LocalTime getTijd() {
        return tijd;
    }

    public void setTijd(LocalTime tijd) {
        this.tijd = tijd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}

package com.cs.cijferSysteem.domein;


import javax.persistence.*;
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
	private List<ToetsCijfer> cijfers;

    public void voegCijferToe(ToetsCijfer tc) {
    	cijfers.add(tc);
    }


   /* public Docent getDocent() {
        return docent;
    }

    public void setDocent(Docent docent) {
        this.docent = docent;
    }*/

    public List<ToetsCijfer> geefCijfers() {
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

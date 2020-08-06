package com.cs.cijferSysteem.dto;


public class CreateToetsDto {


    private String datum;
    private String vak;
    private String tijd;




    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getTijd() {
        return tijd;
    }

    public void setTijd(String tijd) {
        this.tijd = tijd;
    }

    public String getDatum() {
        return datum;
    }


    public String getVak() {
        return vak;
    }

    public void setVak(String vak) {
        this.vak = vak;
    }
}


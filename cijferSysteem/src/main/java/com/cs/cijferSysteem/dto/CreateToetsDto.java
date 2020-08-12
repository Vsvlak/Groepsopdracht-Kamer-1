package com.cs.cijferSysteem.dto;


public class CreateToetsDto {


    private String datum;
    private Long vakId;
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

    public Long getVakId() {
        return vakId;
    }

    public void setVakId(Long vakId) {
        this.vakId = vakId;
    }
}


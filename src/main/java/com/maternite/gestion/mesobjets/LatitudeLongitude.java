package com.maternite.gestion.mesobjets;

public class LatitudeLongitude {

    // Attributes
    private Double latitude,longitude;
    private String libelle;
    private int numag;
    private int idcord;

    public LatitudeLongitude(Double latitude, Double longitude, String libelle, int numag, int idcord) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.libelle = libelle;
        this.numag = numag;
        this.idcord = idcord;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNumag() {
        return numag;
    }

    public void setNumag(int numag) {
        this.numag = numag;
    }

    public int getIdcord() {
        return idcord;
    }

    public void setIdcord(int idcord) {
        this.idcord = idcord;
    }
}

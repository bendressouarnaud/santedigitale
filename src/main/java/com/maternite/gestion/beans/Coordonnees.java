package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_coordonnees")
public class Coordonnees {

    @Id
    @Column(name = "idcord")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcord;

    @Column(name="imageprop")
    private String imageprop;

    @Column(name="imagemag")
    private String imagemag;

    @Column(name="numag")
    private int numag;

    @Column(name="longitude")
    private Double longitude;

    @Column(name="latitude")
    private Double latitude;

    @Column(name="idven")
    private int idven;

    public Coordonnees() {
    }

    public int getIdcord() {
        return idcord;
    }

    public void setIdcord(int idcord) {
        this.idcord = idcord;
    }

    public String getImageprop() {
        return imageprop;
    }

    public void setImageprop(String imageprop) {
        this.imageprop = imageprop;
    }

    public String getImagemag() {
        return imagemag;
    }

    public void setImagemag(String imagemag) {
        this.imagemag = imagemag;
    }

    public int getNumag() {
        return numag;
    }

    public void setNumag(int numag) {
        this.numag = numag;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getIdven() {
        return idven;
    }

    public void setIdven(int idven) {
        this.idven = idven;
    }
}

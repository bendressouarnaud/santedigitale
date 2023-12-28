package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_constante")
public class Constante {

    @Id
    @Column(name = "idcons")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcons;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "heure")
    private String heure;

    @Column(name="poids")
    private Double poids;

    @Column(name="taille")
    private Double taille;

    @Column(name="tension")
    private Double tension;

    @Column(name="temperature")
    private Double temperature;

    @Column(name="tensionarterielle")
    private String tensionarterielle;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "idmed")
    private int idmed;

    @Column(name = "iddocteur")
    private int iddocteur;

    @Column(name = "idcon")
    private int idcon;

    @Column(name = "idser")
    private int idser;

    @Column(name = "pouls")
    private int pouls;


    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }

    public Constante() {
    }

    public int getIdcons() {
        return idcons;
    }

    public void setIdcons(int idcons) {
        this.idcons = idcons;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Double getTaille() {
        return taille;
    }

    public void setTaille(Double taille) {
        this.taille = taille;
    }

    public Double getTension() {
        return tension;
    }

    public void setTension(Double tension) {
        this.tension = tension;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public int getIddocteur() {
        return iddocteur;
    }

    public void setIddocteur(int iddocteur) {
        this.iddocteur = iddocteur;
    }

    public String getTensionarterielle() {
        return tensionarterielle;
    }

    public void setTensionarterielle(String tensionarterielle) {
        this.tensionarterielle = tensionarterielle;
    }

    public int getIdser() {
        return idser;
    }

    public void setIdser(int idser) {
        this.idser = idser;
    }

    public int getPouls() {
        return pouls;
    }

    public void setPouls(int pouls) {
        this.pouls = pouls;
    }
}

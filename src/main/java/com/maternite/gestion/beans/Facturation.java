package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_facturation")
public class Facturation {

    @Id
    @Column(name = "idfac")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idfac;

    @Column(name = "typeclient")
    private int typeclient;

    @Column(name = "numclient")
    private String numclient;

    @Column(name = "assurance")
    private int assurance;

    @Column(name = "couverture")
    private double couverture;

    @Column(name = "montant")
    private double montant;

    @Column(name = "service")
    private int service;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "idhop")
    private int idhop;

    @Column(name = "idmed")
    private int idmed;

    @Column(name = "heure")
    private String heure;

    public Facturation() {
    }

    public int getIdfac() {
        return idfac;
    }

    public void setIdfac(int idfac) {
        this.idfac = idfac;
    }

    public int getTypeclient() {
        return typeclient;
    }

    public void setTypeclient(int typeclient) {
        this.typeclient = typeclient;
    }

    public String getNumclient() {
        return numclient;
    }

    public void setNumclient(String numclient) {
        this.numclient = numclient;
    }

    public int getAssurance() {
        return assurance;
    }

    public void setAssurance(int assurance) {
        this.assurance = assurance;
    }

    public double getCouverture() {
        return couverture;
    }

    public void setCouverture(double couverture) {
        this.couverture = couverture;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public int getIdhop() {
        return idhop;
    }

    public void setIdhop(int idhop) {
        this.idhop = idhop;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}

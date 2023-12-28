package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_factorisationrdv")
public class Factorisationrdv {

    @Id
    @Column(name = "idfdv")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idfdv;

    @Column(name = "idser")
    private int idser;

    @Column(name = "idmed")
    private int idmed;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "heure")
    private int heure;

    @Column(name = "hopital")
    private int hopital;

    @Column(name = "provenance")
    private int provenance;

    public Factorisationrdv() {
    }

    public int getIdfdv() {
        return idfdv;
    }

    public void setIdfdv(int idfdv) {
        this.idfdv = idfdv;
    }

    public int getIdser() {
        return idser;
    }

    public void setIdser(int idser) {
        this.idser = idser;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public int getHopital() {
        return hopital;
    }

    public void setHopital(int hopital) {
        this.hopital = hopital;
    }

    public int getProvenance() {
        return provenance;
    }

    public void setProvenance(int provenance) {
        this.provenance = provenance;
    }
}

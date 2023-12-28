package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_tacheservice")
public class Tacheservice {

    @Id
    @Column(name = "idtac")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtac;

    @Column(name = "lundi")
    private int lundi;

    @Column(name = "mardi")
    private int mardi;

    @Column(name = "mercredi")
    private int mercredi;

    @Column(name = "jeudi")
    private int jeudi;

    @Column(name = "vendredi")
    private int vendredi;

    @Column(name = "samedi")
    private int samedi;

    @Column(name = "dimanche")
    private int dimanche;

    @Column(name = "idabo")
    private int idabo;

    @Column(name = "heure")
    private int heure;

    @Column(name = "message")
    private String message;

    public Tacheservice() {
    }

    public int getIdtac() {
        return idtac;
    }

    public void setIdtac(int idtac) {
        this.idtac = idtac;
    }

    public int getLundi() {
        return lundi;
    }

    public void setLundi(int lundi) {
        this.lundi = lundi;
    }

    public int getMardi() {
        return mardi;
    }

    public void setMardi(int mardi) {
        this.mardi = mardi;
    }

    public int getMercredi() {
        return mercredi;
    }

    public void setMercredi(int mercredi) {
        this.mercredi = mercredi;
    }

    public int getJeudi() {
        return jeudi;
    }

    public void setJeudi(int jeudi) {
        this.jeudi = jeudi;
    }

    public int getVendredi() {
        return vendredi;
    }

    public void setVendredi(int vendredi) {
        this.vendredi = vendredi;
    }

    public int getSamedi() {
        return samedi;
    }

    public void setSamedi(int samedi) {
        this.samedi = samedi;
    }

    public int getDimanche() {
        return dimanche;
    }

    public void setDimanche(int dimanche) {
        this.dimanche = dimanche;
    }

    public int getIdabo() {
        return idabo;
    }

    public void setIdabo(int idabo) {
        this.idabo = idabo;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

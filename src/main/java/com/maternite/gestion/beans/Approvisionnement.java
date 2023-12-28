package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_approvionnement")
public class Approvisionnement {

    @Id
    @Column(name = "idapp")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idapp;

    @Column(name = "idmd")
    private int idmd;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "heure")
    private String heure;

    @Column(name = "idusr")
    private int idusr;

    @Column(name = "numlot")
    private String numlot;

    @Column(name = "dateperemption")
    private Date dateperemption;

    @Column(name = "prixunitaire")
    private int prixunitaire;

    @Column(name = "quantite")
    private int quantite;

    public Approvisionnement() {
    }

    public int getIdapp() {
        return idapp;
    }

    public void setIdapp(int idapp) {
        this.idapp = idapp;
    }

    public int getIdmd() {
        return idmd;
    }

    public void setIdmd(int idmd) {
        this.idmd = idmd;
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

    public int getIdusr() {
        return idusr;
    }

    public void setIdusr(int idusr) {
        this.idusr = idusr;
    }

    public String getNumlot() {
        return numlot;
    }

    public void setNumlot(String numlot) {
        this.numlot = numlot;
    }

    public Date getDateperemption() {
        return dateperemption;
    }

    public void setDateperemption(Date dateperemption) {
        this.dateperemption = dateperemption;
    }

    public int getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(int prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}

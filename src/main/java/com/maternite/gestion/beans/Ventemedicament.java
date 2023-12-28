package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_ventemedicament")
public class Ventemedicament {

    @Id
    @Column(name = "idvmd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idvmd;

    @Column(name = "idmd")
    private int idmd;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "heure")
    private String heure;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "idtck")
    private int idtck;

    @Column(name = "idusr")
    private int idusr;

    @Column(name = "prix")
    private int prix;

    @Column(name = "idcus")
    private int idcus;

    @Column(name = "idmed")
    private int idmed;

    public Ventemedicament() {
    }

    public int getIdvmd() {
        return idvmd;
    }

    public void setIdvmd(int idvmd) {
        this.idvmd = idvmd;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdtck() {
        return idtck;
    }

    public void setIdtck(int idtck) {
        this.idtck = idtck;
    }

    public int getIdusr() {
        return idusr;
    }

    public void setIdusr(int idusr) {
        this.idusr = idusr;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getIdcus() {
        return idcus;
    }

    public void setIdcus(int idcus) {
        this.idcus = idcus;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }
}

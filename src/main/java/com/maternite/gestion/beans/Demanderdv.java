package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_demanderdv")
public class Demanderdv {

    @Id
    @Column(name = "idddv")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idddv;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "suggestion")
    private String suggestion;

    @Column(name = "idmed")
    private int idmed;

    @Column(name = "idser")
    private int idser;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "idheu")
    private int idheu;

    @Column(name = "secretaire")
    private int secretaire;

    @Column(name = "dates")
    private Date dates;

    public Demanderdv() {
    }

    public int getIdddv() {
        return idddv;
    }

    public void setIdddv(int idddv) {
        this.idddv = idddv;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getIdser() {
        return idser;
    }

    public void setIdser(int idser) {
        this.idser = idser;
    }

    public int getIdheu() {
        return idheu;
    }

    public void setIdheu(int idheu) {
        this.idheu = idheu;
    }

    public int getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(int secretaire) {
        this.secretaire = secretaire;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}

package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_patient_famille")
public class Patientfamille {

    @Id
    @Column(name = "idpaf")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpaf;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "numero")
    private String numero;

    @Column(name = "email")
    private String email;

    @Column(name = "lienparente")
    private int lienparente;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "idparent")
    private int idparent;

    public Patientfamille() {
    }

    public int getIdpaf() {
        return idpaf;
    }

    public void setIdpaf(int idpaf) {
        this.idpaf = idpaf;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLienparente() {
        return lienparente;
    }

    public void setLienparente(int lienparente) {
        this.lienparente = lienparente;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getIdparent() {
        return idparent;
    }

    public void setIdparent(int idparent) {
        this.idparent = idparent;
    }
}

package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_pharmacie")
public class Pharmacie {

    @Id
    @Column(name = "idpharm")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpharm;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "email")
    private String email;

    @Column(name = "numero")
    private String numero;

    @Column(name = "actif")
    private int actif;

    public Pharmacie() {
    }

    public int getIdpharm() {
        return idpharm;
    }

    public void setIdpharm(int idpharm) {
        this.idpharm = idpharm;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getActif() {
        return actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }
}

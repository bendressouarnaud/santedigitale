package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_medicament")
public class Medicament {

    @Id
    @Column(name = "idmd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmd;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "prix")
    private int prix;

    @Column(name = "libelle")
    private String libelle;

    public Medicament() {
    }

    public int getIdmd() {
        return idmd;
    }

    public void setIdmd(int idmd) {
        this.idmd = idmd;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}

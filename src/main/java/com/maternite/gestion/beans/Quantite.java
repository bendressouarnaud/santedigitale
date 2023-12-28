package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_quantite")
public class Quantite {

    // Attributes :
    @Id
    @Column(name = "idqte")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idqte;

    @Column(name = "libelle")
    private String libelle;

    public Quantite() {
    }

    public int getIdqte() {
        return idqte;
    }

    public void setIdqte(int idqte) {
        this.idqte = idqte;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
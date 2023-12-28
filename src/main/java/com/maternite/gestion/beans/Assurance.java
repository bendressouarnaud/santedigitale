package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_assurance")
public class Assurance {

    @Id
    @Column(name = "idass")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idass;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "actif")
    private int actif;

    public Assurance() {
    }

    public int getIdass() {
        return idass;
    }

    public void setIdass(int idass) {
        this.idass = idass;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getActif() {
        return actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }
}

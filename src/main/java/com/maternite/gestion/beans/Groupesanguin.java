package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_groupesanguin")
public class Groupesanguin {

    @Id
    @Column(name = "idgsn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idgsn;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "actif")
    private int actif;

    public Groupesanguin() {
    }

    public int getIdgsn() {
        return idgsn;
    }

    public void setIdgsn(int idgsn) {
        this.idgsn = idgsn;
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

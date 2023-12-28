package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_posologie")
public class Posologie {

    // Attributes :
    @Id
    @Column(name = "idpos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpos;

    @Column(name = "libelle")
    private String libelle;

    public Posologie() {
    }

    public int getIdpos() {
        return idpos;
    }

    public void setIdpos(int idpos) {
        this.idpos = idpos;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

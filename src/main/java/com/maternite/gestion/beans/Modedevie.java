package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_modevie")
public class Modedevie {

    @Id
    @Column(name = "idmvie")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmvie;

    @Column(name = "libelle")
    private String libelle;

    public Modedevie() {
    }

    public int getIdmvie() {
        return idmvie;
    }

    public void setIdmvie(int idmvie) {
        this.idmvie = idmvie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

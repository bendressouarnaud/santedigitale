package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_heure")
public class Heure {

    @Id
    @Column(name = "idheu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idheu;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "active")
    private int active;

    public int getIdheu() {
        return idheu;
    }

    public void setIdheu(int idheu) {
        this.idheu = idheu;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Heure() {
    }
}

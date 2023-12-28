package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_dosage")
public class Dosage {

    // Attributes :
    @Id
    @Column(name = "iddos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddos;

    @Column(name = "libelle")
    private String libelle;

    public Dosage() {
    }

    public int getIddos() {
        return iddos;
    }

    public void setIddos(int iddos) {
        this.iddos = iddos;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

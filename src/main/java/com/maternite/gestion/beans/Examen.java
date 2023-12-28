package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_examen")
public class Examen {

    @Id
    @Column(name = "idexam")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idexam;

    @Column(name = "libelle")
    private String libelle;

    public Examen() {
    }

    public int getIdexam() {
        return idexam;
    }

    public void setIdexam(int idexam) {
        this.idexam = idexam;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

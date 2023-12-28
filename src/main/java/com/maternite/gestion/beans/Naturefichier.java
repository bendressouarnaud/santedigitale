package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_naturefichier")
public class Naturefichier {

    // anf_naturefichier

    @Id
    @Column(name = "idntf")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idntf;

    @Column(name = "nom")
    private String nom;

    public int getIdntf() {
        return idntf;
    }

    public void setIdntf(int idntf) {
        this.idntf = idntf;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Naturefichier() {
    }
}

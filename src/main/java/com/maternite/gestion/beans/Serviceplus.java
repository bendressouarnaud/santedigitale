package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_serviceplus")
public class Serviceplus {

    @Id
    @Column(name = "idsps")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idsps;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "actif")
    private int actif;

    public Serviceplus() {
    }

    public int getIdsps() {
        return idsps;
    }

    public void setIdsps(int idsps) {
        this.idsps = idsps;
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

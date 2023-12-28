package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_services")
public class Services {

    @Id
    @Column(name = "idser")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idser;

    @Column(name = "libelle")
    private String libelle;

    public Services() {
    }

    public int getIdser() {
        return idser;
    }

    public void setIdser(int idser) {
        this.idser = idser;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

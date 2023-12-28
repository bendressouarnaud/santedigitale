package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_customer")
public class AnfCustomer {

    @Id
    @Column(name = "idcus")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcus;

    @Column(name = "nom")
    private String nom;

    public Integer getIdcus() {
        return this.idcus;
    }

    public void setIdcus(Integer idcus) {
        this.idcus = idcus;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

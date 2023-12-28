package com.maternite.gestion.beans;


import javax.persistence.*;

@Entity(name = "anf_commune")
public class Commune {

    @Id
    @Column(name = "idcom")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcom;

    @Column(name = "libelle")
    private String libelle;

    public Commune(){}

    public int getIdcom() { return idcom;    }
    public void setIdcom(int idcom) { this.idcom = idcom;    }
    public String getLibelle() { return libelle;    }
    public void setLibelle(String libelle) { this.libelle = libelle;    }
}

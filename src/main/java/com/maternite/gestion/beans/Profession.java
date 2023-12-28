package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_profession")
public class Profession {

    // Attributes :
    @Id
    @Column(name = "idprof")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idprof;

    @Column(name = "libelle")
    private String libelle;

    public Profession(){}

    public int getIdprof() { return idprof;    }
    public void setIdprof(int idprof) { this.idprof = idprof;    }
    public String getLibelle() { return libelle;    }
    public void setLibelle(String libelle) { this.libelle = libelle;    }

}

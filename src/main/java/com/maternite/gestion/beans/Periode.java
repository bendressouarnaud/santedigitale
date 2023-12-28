package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_periode")
public class Periode {

    @Id
    @Column(name = "idper")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idper;

    @Column(name = "libelle")
    private String libelle;


    // M E T H O D S :
    public int getIdper() { return idper;    }
    public void setIdper(int idper) { this.idper = idper;    }

    public String getLibelle() { return libelle;    }
    public void setLibelle(String libelle) { this.libelle = libelle;    }

    public Periode(){}

}

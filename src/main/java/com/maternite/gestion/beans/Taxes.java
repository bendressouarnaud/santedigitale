package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_taxe")
public class Taxes {

    @Id
    @Column(name = "idtaxe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itaxe;

    @Column(name = "libelle")
    private String libelle;


    // M E T H O D S :
    public int getItaxe() { return itaxe;    }
    public void setItaxe(int itaxe) { this.itaxe = itaxe;    }

    public String getLibelle() { return libelle;    }
    public void setLibelle(String libelle) { this.libelle = libelle;    }

    public Taxes(){}
}

package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_marche")
public class Marche {

    @Id
    @Column(name = "idmar")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmar;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "idcom")
    private int idcom;


    // Setters and Getters :
    public int getIdmar() { return idmar;    }
    public void setIdmar(int idmar) { this.idmar = idmar;    }

    public String getLibelle() { return libelle;    }
    public void setLibelle(String libelle) { this.libelle = libelle;    }

    public int getIdcom() { return idcom;    }
    public void setIdcom(int idcom) { this.idcom = idcom;    }

    public Marche(){}
}

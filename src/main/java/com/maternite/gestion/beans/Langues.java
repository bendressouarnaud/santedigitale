package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_langues")
public class Langues {

    // Attributes :
    @Id
    @Column(name = "idlan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idlan;

    @Column(name = "libelle")
    private String libelle;

    public Langues(){}

    public Long getIdlan() { return idlan;    }
    public void setIdlan(Long idlan) { this.idlan = idlan;    }
    public String getLibelle() { return libelle;    }
    public void setLibelle(String libelle) { this.libelle = libelle;    }

}

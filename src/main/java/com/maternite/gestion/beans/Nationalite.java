package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_nationalite")
public class Nationalite {

    // Attributes :
    @Id
    @Column(name = "idnat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idnat;

    @Column(name = "libelle")
    private String libelle;

    public Nationalite(){}

    public Long getIdnat() { return idnat;    }
    public void setIdnat(Long idnat) { this.idnat = idnat;    }
    public String getLibelle() { return libelle;    }
    public void setLibelle(String libelle) { this.libelle = libelle;    }

}

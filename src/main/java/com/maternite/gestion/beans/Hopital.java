package com.maternite.gestion.beans;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity(name = "anf_hopital")
public class Hopital {

    @Id
    @Column(name = "idhop")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idhop;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "idcom")
    private int idcom;

    public Long getIdhop() { return idhop;    }
    public void setIdhop(Long idhop) { this.idhop = idhop;    }
    public String getLibelle() { return libelle;    }
    public void setLibelle(String libelle) { this.libelle = libelle;    }
    public int getIdcom() { return idcom;    }
    public void setIdcom(int idcom) { this.idcom = idcom;    }

    public Hopital(){}
}

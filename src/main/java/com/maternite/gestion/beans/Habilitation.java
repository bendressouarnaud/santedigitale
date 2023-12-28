package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_habilitation")
public class Habilitation {

    @Id
    @Column(name = "idhab")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idhab;

    @Column(name = "idadmin")
    private int idadmin;

    @Column(name = "idmed")
    private int idmed;

    public int getIdhab() {
        return idhab;
    }

    public void setIdhab(int idhab) {
        this.idhab = idhab;
    }

    public int getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public Habilitation() {
    }

}

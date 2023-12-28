package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_affec_service")
public class Affectationserv {

    @Id
    @Column(name = "idafs")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idafs;

    @Column(name = "idmed")
    private int idmed;

    @Column(name = "idser")
    private int idser;

    public Affectationserv() {
    }

    public int getIdafs() {
        return idafs;
    }

    public void setIdafs(int idafs) {
        this.idafs = idafs;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public int getIdser() {
        return idser;
    }

    public void setIdser(int idser) {
        this.idser = idser;
    }
}

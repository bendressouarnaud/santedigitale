package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_assignationpharm")
public class Assignationpharm {

    @Id
    @Column(name = "idaph")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idaph;

    @Column(name = "idmed")
    private int idmed;

    @Column(name = "idpharm")
    private int idpharm;

    public Assignationpharm() {
    }

    public int getIdaph() {
        return idaph;
    }

    public void setIdaph(int idaph) {
        this.idaph = idaph;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public int getIdpharm() {
        return idpharm;
    }

    public void setIdpharm(int idpharm) {
        this.idpharm = idpharm;
    }
}

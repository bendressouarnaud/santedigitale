package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_assignation")
public class Assignation {

    @Id
    @Column(name = "idass")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idass;

    @Column(name = "idmed")
    private int idmed;

    @Column(name = "idhop")
    private int idhop;

    public Assignation() {
    }

    public int getIdass() {
        return idass;
    }

    public void setIdass(int idass) {
        this.idass = idass;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public int getIdhop() {
        return idhop;
    }

    public void setIdhop(int idhop) {
        this.idhop = idhop;
    }
}

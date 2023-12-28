package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_affectation")
public class Affectation {

    @Id
    @Column(name = "idaff")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idaff;

    @Column(name = "idmed")
    private int idmed;

    @Column(name = "idhop")
    private int idhop;

    public Affectation() {
    }

    public int getIdaff() {
        return idaff;
    }

    public void setIdaff(int idaff) {
        this.idaff = idaff;
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

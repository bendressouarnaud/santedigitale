package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_tamponmdecin")
public class Tampons {

    @Id
    @Column(name = "idtpm")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtpm;

    @Column(name = "idmed")
    private int idmed;

    @Column(name = "tampon")
    private String tampon;

    public Tampons() {
    }

    public int getIdtpm() {
        return idtpm;
    }

    public void setIdtpm(int idtpm) {
        this.idtpm = idtpm;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public String getTampon() {
        return tampon;
    }

    public void setTampon(String tampon) {
        this.tampon = tampon;
    }
}

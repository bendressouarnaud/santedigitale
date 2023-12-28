package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_qrcode")
public class Qcodes {

    @Id
    @Column(name = "idqrc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idqrc;

    @Column(name = "idcon")
    private int idcon;

    @Column(name = "idpharm")
    private int idpharm;

    @Column(name = "image")
    private String image;

    public Qcodes() {
    }

    public int getIdqrc() {
        return idqrc;
    }

    public void setIdqrc(int idqrc) {
        this.idqrc = idqrc;
    }

    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdpharm() {
        return idpharm;
    }

    public void setIdpharm(int idpharm) {
        this.idpharm = idpharm;
    }
}

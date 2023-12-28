package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_seuil")
public class Seuil {

    @Id
    @Column(name = "idseu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idseu;

    @Column(name = "idusr")
    private int idusr;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "seuil")
    private int seuil;

    public Seuil() {
    }

    public int getIdseu() {
        return idseu;
    }

    public void setIdseu(int idseu) {
        this.idseu = idseu;
    }

    public int getIdusr() {
        return idusr;
    }

    public void setIdusr(int idusr) {
        this.idusr = idusr;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getSeuil() {
        return seuil;
    }

    public void setSeuil(int seuil) {
        this.seuil = seuil;
    }
}

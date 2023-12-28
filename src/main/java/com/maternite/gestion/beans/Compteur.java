package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_compteur")
public class Compteur {

    @Id
    @Column(name = "idcpr")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcpr;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "valeur")
    private int valeur;

    public Compteur() {
    }

    public int getIdcpr() {
        return idcpr;
    }

    public void setIdcpr(int idcpr) {
        this.idcpr = idcpr;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}

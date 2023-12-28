package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_rdv")
public class Rendezvous {

    @Id
    @Column(name = "idrdv")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrdv;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "heure")
    private int heure;

    @Column(name = "idcon")
    private int idcon;

    public int getIdrdv() {
        return idrdv;
    }

    public void setIdrdv(int idrdv) {
        this.idrdv = idrdv;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }

    public Rendezvous() {
    }
}

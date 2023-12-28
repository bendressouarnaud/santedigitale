package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_interventions")
public class Interventions {

    @Id
    @Column(name = "idint")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idint;

    @Column(name = "idcon")
    private int idcon;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "dent")
    private int dent;

    @Column(name = "nature")
    private String nature;

    public Interventions() {
    }

    public int getIdint() {
        return idint;
    }

    public void setIdint(int idint) {
        this.idint = idint;
    }

    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getDent() {
        return dent;
    }

    public void setDent(int dent) {
        this.dent = dent;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }
}

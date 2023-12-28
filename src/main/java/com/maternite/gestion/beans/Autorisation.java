package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_autorisation")
public class Autorisation {

    @Id
    @Column(name = "idaut")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idaut;

    @Column(name = "idmedorigin")
    private int idmedorigin;

    @Column(name = "idmeddestin")
    private int idmeddestin;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "dates")
    private Date dates;

    public Autorisation() {
    }

    public int getIdaut() {
        return idaut;
    }

    public void setIdaut(int idaut) {
        this.idaut = idaut;
    }

    public int getIdmedorigin() {
        return idmedorigin;
    }

    public void setIdmedorigin(int idmedorigin) {
        this.idmedorigin = idmedorigin;
    }

    public int getIdmeddestin() {
        return idmeddestin;
    }

    public void setIdmeddestin(int idmeddestin) {
        this.idmeddestin = idmeddestin;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }
}

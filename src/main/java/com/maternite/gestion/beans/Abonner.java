package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_abonner")
public class Abonner {

    @Id
    @Column(name = "idabo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idabo;

    @Column(name = "idsps")
    private int idsps;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "dates")
    private Date dates;

    public Abonner() {
    }

    public int getIdabo() {
        return idabo;
    }

    public void setIdabo(int idabo) {
        this.idabo = idabo;
    }

    public int getIdsps() {
        return idsps;
    }

    public void setIdsps(int idsps) {
        this.idsps = idsps;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
}

package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_patient_wallet")
public class Patientwallet {

    @Id
    @Column(name = "idpwa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpwa;

    @Column(name = "montant")
    private int montant;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "idmed")
    private int idmed;

    @Column(name = "dates")
    private Date dates;

    public Patientwallet() {
    }

    public int getIdpwa() {
        return idpwa;
    }

    public void setIdpwa(int idpwa) {
        this.idpwa = idpwa;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
}

package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_patient_wallet_state")
public class Patientwalletstate {

    @Id
    @Column(name = "idpws")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpws;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "montant")
    private Double montant;

    public Patientwalletstate() {
    }

    public int getIdpws() {
        return idpws;
    }

    public void setIdpws(int idpws) {
        this.idpws = idpws;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
}

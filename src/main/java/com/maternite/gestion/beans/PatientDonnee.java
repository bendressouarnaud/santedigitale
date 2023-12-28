package com.maternite.gestion.beans;

public class PatientDonnee {

    // Attributes :
    int idpat;
    Double montant;

    // Methods :
    public PatientDonnee() {
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

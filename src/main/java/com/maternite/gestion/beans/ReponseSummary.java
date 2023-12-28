package com.maternite.gestion.beans;

public class ReponseSummary {

    int rdv, membre, reservation, consultation;

    public ReponseSummary() {
    }

    public int getRdv() {
        return rdv;
    }

    public void setRdv(int rdv) {
        this.rdv = rdv;
    }

    public int getMembre() {
        return membre;
    }

    public void setMembre(int membre) {
        this.membre = membre;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public int getConsultation() {
        return consultation;
    }

    public void setConsultation(int consultation) {
        this.consultation = consultation;
    }
}

package com.maternite.gestion.beans;

public class Reponserdvrqt {

    String jour, heure, medecin, service;
    int idddv;

    public Reponserdvrqt() {
        this.idddv = 0;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getMedecin() {
        return medecin;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getIdddv() {
        return idddv;
    }

    public void setIdddv(int idddv) {
        this.idddv = idddv;
    }
}

package com.maternite.gestion.mesobjets;

public class ObjetMedecin {

    int idmed;
    String nom;

    public ObjetMedecin(int idmed, String nom) {
        this.idmed = idmed;
        this.nom = nom;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

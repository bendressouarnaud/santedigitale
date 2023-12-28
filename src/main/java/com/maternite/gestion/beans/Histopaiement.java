package com.maternite.gestion.beans;

public class Histopaiement {

    // Attributs :
    public String libelle,dates,heure,periodes,nom;
    public int montant;

    // MEthodes :
    public Histopaiement(){}

    public String getLibelle(){ return libelle; }
    public String getDates(){ return dates; }
    public String getHeure(){ return heure; }
    public String getPeriodes(){ return periodes; }
    public String getNom(){ return nom; }

    public int getMontant(){ return montant; }


}

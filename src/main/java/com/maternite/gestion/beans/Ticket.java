package com.maternite.gestion.beans;

public class Ticket {

    // Attributs :
    public String taxe,dte,heure,nom,contact;
    public int montant;

    // MEthodes :
    public Ticket(){}

    public String getTaxe(){ return taxe; }
    public String getDte(){ return dte; }
    public String getHeure(){ return heure; }
    public String getContact(){ return contact; }
    public String getNom(){ return nom; }
    public int getMontant(){ return montant; }

}

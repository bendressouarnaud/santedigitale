package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_ordonnance")
public class Ordonnance {

    @Id
    @Column(name = "idord")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idord;

    @Column(name = "prescription")
    private String prescription;

    @Column(name = "quantite")
    private int quantite;
    @Column(name = "posologie")
    private int posologie;
    @Column(name = "dosage")
    private int dosage;
    @Column(name = "typemedic")
    private int typemedic;

    @Column(name = "idcon")
    private int idcon;

    @Column(name = "idpharm")
    private int idpharm;

    @Column(name = "dates")
    private Date dates;

    public Ordonnance() {
    }

    public int getIdord() {
        return idord;
    }

    public void setIdord(int idord) {
        this.idord = idord;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }


    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPosologie() {
        return posologie;
    }

    public void setPosologie(int posologie) {
        this.posologie = posologie;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getTypemedic() {
        return typemedic;
    }

    public void setTypemedic(int typemedic) {
        this.typemedic = typemedic;
    }

    public int getIdpharm() {
        return idpharm;
    }

    public void setIdpharm(int idpharm) {
        this.idpharm = idpharm;
    }
}

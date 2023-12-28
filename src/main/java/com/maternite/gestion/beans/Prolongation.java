package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_prolongation")
public class Prolongation {

    @Id
    @Column(name = "idpro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpro;

    @Column(name = "idcon")
    private int idcon;

    @Column(name = "motif")
    private String motif;

    @Column(name = "suspicion")
    private String suspicion;

    @Column(name = "diagnosticevoq")
    private String diagnosticevoq;
    @Column(name = "radiologiques")
    private String radiologiques;
    @Column(name = "biologiques")
    private String biologiques;

    @Column(name = "jour")
    private int jour;

    @Column(name = "dates")
    private Date dates;

    public Prolongation() {
    }

    public int getIdpro() {
        return idpro;
    }

    public void setIdpro(int idpro) {
        this.idpro = idpro;
    }

    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getSuspicion() {
        return suspicion;
    }

    public void setSuspicion(String suspicion) {
        this.suspicion = suspicion;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getDiagnosticevoq() {
        return diagnosticevoq;
    }

    public void setDiagnosticevoq(String diagnosticevoq) {
        this.diagnosticevoq = diagnosticevoq;
    }

    public String getRadiologiques() {
        return radiologiques;
    }

    public void setRadiologiques(String radiologiques) {
        this.radiologiques = radiologiques;
    }

    public String getBiologiques() {
        return biologiques;
    }

    public void setBiologiques(String biologiques) {
        this.biologiques = biologiques;
    }
}

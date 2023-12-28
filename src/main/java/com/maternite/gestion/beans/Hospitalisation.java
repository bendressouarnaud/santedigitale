package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_hospitalisation")
public class Hospitalisation {

    @Id
    @Column(name = "idhos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idhos;

    @Column(name = "chambre")
    private String chambre;

    @Column(name = "lit")
    private String lit;

    @Column(name = "datentree")
    private Date datentree;

    @Column(name = "heure")
    private String heure;

    @Column(name = "motif")
    private String motif;

    @Column(name = "bilanbio")
    private String bilanbio;

    @Column(name = "bilanradio")
    private String bilanradio;

    @Column(name = "nbrejour")
    private int nbrejour;

    @Column(name = "idcon")
    private int idcon;

    public Hospitalisation() {
    }

    public int getIdhos() {
        return idhos;
    }

    public void setIdhos(int idhos) {
        this.idhos = idhos;
    }

    public String getChambre() {
        return chambre;
    }

    public void setChambre(String chambre) {
        this.chambre = chambre;
    }

    public String getLit() {
        return lit;
    }

    public void setLit(String lit) {
        this.lit = lit;
    }

    public Date getDatentree() {
        return datentree;
    }

    public void setDatentree(Date datentree) {
        this.datentree = datentree;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getBilanbio() {
        return bilanbio;
    }

    public void setBilanbio(String bilanbio) {
        this.bilanbio = bilanbio;
    }

    public String getBilanradio() {
        return bilanradio;
    }

    public void setBilanradio(String bilanradio) {
        this.bilanradio = bilanradio;
    }

    public int getNbrejour() {
        return nbrejour;
    }

    public void setNbrejour(int nbrejour) {
        this.nbrejour = nbrejour;
    }

    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }
}

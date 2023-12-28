package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_cabinetdentaire")
public class Cabinetdentaire {

    @Id
    @Column(name = "idcde")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcde;

    @Column(name = "idcon")
    private int idcon;

    @Column(name = "cardiopathie")
    private String cardiopathie;

    @Column(name = "troubles")
    private String troubles;
    @Column(name = "hta")
    private String hta;
    @Column(name = "diabetedent")
    private String diabetedent;
    @Column(name = "asthme")
    private String asthme;
    @Column(name = "infectionsorl")
    private String infectionsorl;
    @Column(name = "vih")
    private String vih;
    @Column(name = "antibiotique")
    private String antibiotique;
    @Column(name = "anesthesie")
    private String anesthesie;
    @Column(name = "quinine")
    private String quinine;
    @Column(name = "latex")
    private String latex;
    @Column(name = "autresdentaire")
    private String autresdentaire;

    @Column(name = "hygienebuccale")
    private int hygienebuccale;
    @Column(name = "articuledentaire")
    private int articuledentaire;
    @Column(name = "langue")
    private int langue;
    @Column(name = "suceur")
    private int suceur;
    @Column(name = "orthodontique")
    private int orthodontique;

    @Column(name = "dentsmanquantes")
    private String dentsmanquantes;
    @Column(name = "appareillage")
    private int appareillage;

    @Column(name = "idpat")
    private int idpat;

    public Cabinetdentaire() {
    }

    public int getIdcde() {
        return idcde;
    }

    public void setIdcde(int idcde) {
        this.idcde = idcde;
    }

    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }

    public String getCardiopathie() {
        return cardiopathie;
    }

    public void setCardiopathie(String cardiopathie) {
        this.cardiopathie = cardiopathie;
    }

    public String getTroubles() {
        return troubles;
    }

    public void setTroubles(String troubles) {
        this.troubles = troubles;
    }

    public String getHta() {
        return hta;
    }

    public void setHta(String hta) {
        this.hta = hta;
    }

    public String getDiabetedent() {
        return diabetedent;
    }

    public void setDiabetedent(String diabetedent) {
        this.diabetedent = diabetedent;
    }

    public String getAsthme() {
        return asthme;
    }

    public void setAsthme(String asthme) {
        this.asthme = asthme;
    }

    public String getInfectionsorl() {
        return infectionsorl;
    }

    public void setInfectionsorl(String infectionsorl) {
        this.infectionsorl = infectionsorl;
    }

    public String getVih() {
        return vih;
    }

    public void setVih(String vih) {
        this.vih = vih;
    }

    public String getAntibiotique() {
        return antibiotique;
    }

    public void setAntibiotique(String antibiotique) {
        this.antibiotique = antibiotique;
    }

    public String getAnesthesie() {
        return anesthesie;
    }

    public void setAnesthesie(String anesthesie) {
        this.anesthesie = anesthesie;
    }

    public String getQuinine() {
        return quinine;
    }

    public void setQuinine(String quinine) {
        this.quinine = quinine;
    }

    public String getLatex() {
        return latex;
    }

    public void setLatex(String latex) {
        this.latex = latex;
    }

    public String getAutresdentaire() {
        return autresdentaire;
    }

    public void setAutresdentaire(String autresdentaire) {
        this.autresdentaire = autresdentaire;
    }

    public int getHygienebuccale() {
        return hygienebuccale;
    }

    public void setHygienebuccale(int hygienebuccale) {
        this.hygienebuccale = hygienebuccale;
    }

    public int getArticuledentaire() {
        return articuledentaire;
    }

    public void setArticuledentaire(int articuledentaire) {
        this.articuledentaire = articuledentaire;
    }

    public int getLangue() {
        return langue;
    }

    public void setLangue(int langue) {
        this.langue = langue;
    }

    public int getSuceur() {
        return suceur;
    }

    public void setSuceur(int suceur) {
        this.suceur = suceur;
    }

    public int getOrthodontique() {
        return orthodontique;
    }

    public void setOrthodontique(int orthodontique) {
        this.orthodontique = orthodontique;
    }

    public String getDentsmanquantes() {
        return dentsmanquantes;
    }

    public void setDentsmanquantes(String dentsmanquantes) {
        this.dentsmanquantes = dentsmanquantes;
    }

    public int getAppareillage() {
        return appareillage;
    }

    public void setAppareillage(int appareillage) {
        this.appareillage = appareillage;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }
}

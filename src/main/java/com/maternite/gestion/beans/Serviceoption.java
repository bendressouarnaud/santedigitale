package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_serviceoption")
public class Serviceoption {

    @Id
    @Column(name = "idsop")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idsop;

    @Column(name = "motif")
    private int motif;

    @Column(name = "antecedent")
    private int antecedent;

    @Column(name = "modevie")
    private int modevie;

    @Column(name = "examenclinique")
    private int examenclinique;

    @Column(name = "conclusion")
    private int conclusion;

    @Column(name = "diagnostic")
    private int diagnostic;

    @Column(name = "traitement")
    private int traitement;

    @Column(name = "interrogatoire")
    private int interrogatoire;

    @Column(name = "examenphysique")
    private int examenphysique;

    @Column(name = "conduiteatenir")
    private int conduiteatenir;

    @Column(name = "diagnosticretenu")
    private int diagnosticretenu;

    @Column(name = "hospitalisation")
    private int hospitalisation;

    @Column(name = "soins")
    private int soins;

    @Column(name = "avisconfrere")
    private int avisconfrere;

    @Column(name = "ordonnance")
    private int ordonnance;
    /*    */
    @Column(name = "passemedical")
    private int passemedical;
    @Column(name = "allergies")
    private int allergies;
    @Column(name = "passedentaire")
    private int passedentaire;
    @Column(name = "protheses")
    private int protheses;

    // interventions
    @Column(name = "interventions")
    private int interventions;

    @Column(name = "examparaclinique")
    private int examparaclinique;

    @Column(name = "idser")
    private int idser;

    @Column(name = "idhop")
    private int idhop;


    // M e t h o d s :
    public Serviceoption() {
    }

    public int getIdser() {
        return idser;
    }

    public void setIdser(int idser) {
        this.idser = idser;
    }

    public int getIdsop() {
        return idsop;
    }

    public void setIdsop(int idsop) {
        this.idsop = idsop;
    }

    public int getMotif() {
        return motif;
    }

    public void setMotif(int motif) {
        this.motif = motif;
    }

    public int getAntecedent() {
        return antecedent;
    }

    public void setAntecedent(int antecedent) {
        this.antecedent = antecedent;
    }

    public int getModevie() {
        return modevie;
    }

    public void setModevie(int modevie) {
        this.modevie = modevie;
    }

    public int getExamenclinique() {
        return examenclinique;
    }

    public void setExamenclinique(int examenclinique) {
        this.examenclinique = examenclinique;
    }

    public int getConclusion() {
        return conclusion;
    }

    public void setConclusion(int conclusion) {
        this.conclusion = conclusion;
    }

    public int getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(int diagnostic) {
        this.diagnostic = diagnostic;
    }

    public int getTraitement() {
        return traitement;
    }

    public void setTraitement(int traitement) {
        this.traitement = traitement;
    }

    public int getIdhop() {
        return idhop;
    }

    public void setIdhop(int idhop) {
        this.idhop = idhop;
    }

    public int getInterrogatoire() {
        return interrogatoire;
    }

    public void setInterrogatoire(int interrogatoire) {
        this.interrogatoire = interrogatoire;
    }

    public int getExamenphysique() {
        return examenphysique;
    }

    public void setExamenphysique(int examenphysique) {
        this.examenphysique = examenphysique;
    }

    public int getConduiteatenir() {
        return conduiteatenir;
    }

    public void setConduiteatenir(int conduiteatenir) {
        this.conduiteatenir = conduiteatenir;
    }

    public int getDiagnosticretenu() {
        return diagnosticretenu;
    }

    public void setDiagnosticretenu(int diagnosticretenu) {
        this.diagnosticretenu = diagnosticretenu;
    }

    public int getHospitalisation() {
        return hospitalisation;
    }

    public void setHospitalisation(int hospitalisation) {
        this.hospitalisation = hospitalisation;
    }

    public int getSoins() {
        return soins;
    }

    public void setSoins(int soins) {
        this.soins = soins;
    }

    public int getAvisconfrere() {
        return avisconfrere;
    }

    public void setAvisconfrere(int avisconfrere) {
        this.avisconfrere = avisconfrere;
    }

    public int getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(int ordonnance) {
        this.ordonnance = ordonnance;
    }

    public int getPassemedical() {
        return passemedical;
    }

    public void setPassemedical(int passemedical) {
        this.passemedical = passemedical;
    }

    public int getAllergies() {
        return allergies;
    }

    public void setAllergies(int allergies) {
        this.allergies = allergies;
    }

    public int getPassedentaire() {
        return passedentaire;
    }

    public void setPassedentaire(int passedentaire) {
        this.passedentaire = passedentaire;
    }

    public int getProtheses() {
        return protheses;
    }

    public void setProtheses(int protheses) {
        this.protheses = protheses;
    }

    public int getInterventions() {
        return interventions;
    }

    public void setInterventions(int interventions) {
        this.interventions = interventions;
    }

    public int getExamparaclinique() {
        return examparaclinique;
    }

    public void setExamparaclinique(int examparaclinique) {
        this.examparaclinique = examparaclinique;
    }
}

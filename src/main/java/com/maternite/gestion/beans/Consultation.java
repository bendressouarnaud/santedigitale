package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_consultation")
public class Consultation {

    @Id
    @Column(name = "idcon")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcon;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "dateconsultation")
    private Date dateconsultation;

    @Column(name = "heure")
    private String heure;

    @Column(name = "symptome")
    private String symptome;

    @Column(name = "idhop")
    private int idhop;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "idser")
    private int idser;

    @Column(name = "idmed")
    private int idmed;

    @Column(name = "envoyerpar")
    private String envoyerpar;

    @Column(name = "motif")
    private String motif;
    @Column(name = "diabete")
    private int diabete;
    @Column(name = "hypertension")
    private int hypertension;
    @Column(name = "pblcardiaque")
    private int pblcardiaque;
    @Column(name = "pblpulmonaire")
    private int pblpulmonaire;
    @Column(name = "drepanocytose")
    private int drepanocytose;
    @Column(name = "autres")
    private int autres;
    @Column(name = "autrescommentaire")
    private String autrescommentaire;
    @Column(name = "modevie")
    private int modevie;
    @Column(name = "examenclinique")
    private String examenclinique;
    @Column(name = "conclusion")
    private String conclusion;
    @Column(name = "traitement")
    private String traitement;
    //
    @Column(name = "interrogatoire")
    private String interrogatoire;
    @Column(name = "examenphysique")
    private String examenphysique;
    @Column(name = "conduiteatenir")
    private String conduiteatenir;

    //
    @Column(name = "tabagismeactif")
    private int tabagismeactif;
    @Column(name = "tabagismepassif")
    private int tabagismepassif;
    @Column(name = "ethylisme")
    private int ethylisme;
    @Column(name = "sedentaire")
    private int sedentaire;
    @Column(name = "autresmode")
    private int autresmode;
    @Column(name = "autresmodecomment")
    private String autresmodecomment;

    /*    28/11/2020    */
    @Column(name = "diagnosticretenu")
    private String diagnosticretenu;
    @Column(name = "hospitalisation")
    private int hospitalisation;
    @Column(name = "soins")
    private String soins;

    @Column(name = "avisconfrere")
    private String avisconfrere;

    @Column(name = "ordonnance")
    private String ordonnance;

    @Column(name = "renseignementsclin")
    private String renseignementsclin;

    public Consultation() {
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

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getSymptome() {
        return symptome;
    }

    public void setSymptome(String symptome) {
        this.symptome = symptome;
    }

    public int getIdhop() {
        return idhop;
    }

    public void setIdhop(int idhop) {
        this.idhop = idhop;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public int getIdmed() {
        return idmed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public String getEnvoyerpar() {
        return envoyerpar;
    }

    public void setEnvoyerpar(String envoyerpar) {
        this.envoyerpar = envoyerpar;
    }

    public int getIdser() {
        return idser;
    }

    public Date getDateconsultation() {
        return dateconsultation;
    }

    public void setDateconsultation(Date dateconsultation) {
        this.dateconsultation = dateconsultation;
    }

    public void setIdser(int idser) {
        this.idser = idser;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getDiabete() {
        return diabete;
    }

    public void setDiabete(int diabete) {
        this.diabete = diabete;
    }

    public int getHypertension() {
        return hypertension;
    }

    public void setHypertension(int hypertension) {
        this.hypertension = hypertension;
    }

    public int getPblcardiaque() {
        return pblcardiaque;
    }

    public void setPblcardiaque(int pblcardiaque) {
        this.pblcardiaque = pblcardiaque;
    }

    public int getPblpulmonaire() {
        return pblpulmonaire;
    }

    public void setPblpulmonaire(int pblpulmonaire) {
        this.pblpulmonaire = pblpulmonaire;
    }

    public int getDrepanocytose() {
        return drepanocytose;
    }

    public void setDrepanocytose(int drepanocytose) {
        this.drepanocytose = drepanocytose;
    }

    public int getAutres() {
        return autres;
    }

    public void setAutres(int autres) {
        this.autres = autres;
    }

    public String getAutrescommentaire() {
        return autrescommentaire;
    }

    public void setAutrescommentaire(String autrescommentaire) {
        this.autrescommentaire = autrescommentaire;
    }

    public int getModevie() {
        return modevie;
    }

    public void setModevie(int modevie) {
        this.modevie = modevie;
    }

    public String getExamenclinique() {
        return examenclinique;
    }

    public void setExamenclinique(String examenclinique) {
        this.examenclinique = examenclinique;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public int getTabagismeactif() {
        return tabagismeactif;
    }

    public void setTabagismeactif(int tabagismeactif) {
        this.tabagismeactif = tabagismeactif;
    }

    public int getTabagismepassif() {
        return tabagismepassif;
    }

    public void setTabagismepassif(int tabagismepassif) {
        this.tabagismepassif = tabagismepassif;
    }

    public int getEthylisme() {
        return ethylisme;
    }

    public void setEthylisme(int ethylisme) {
        this.ethylisme = ethylisme;
    }

    public int getSedentaire() {
        return sedentaire;
    }

    public void setSedentaire(int sedentaire) {
        this.sedentaire = sedentaire;
    }

    public int getAutresmode() {
        return autresmode;
    }

    public void setAutresmode(int autresmode) {
        this.autresmode = autresmode;
    }

    public String getAutresmodecomment() {
        return autresmodecomment;
    }

    public void setAutresmodecomment(String autresmodecomment) {
        this.autresmodecomment = autresmodecomment;
    }

    public String getInterrogatoire() {
        return interrogatoire;
    }

    public void setInterrogatoire(String interrogatoire) {
        this.interrogatoire = interrogatoire;
    }

    public String getExamenphysique() {
        return examenphysique;
    }

    public void setExamenphysique(String examenphysique) {
        this.examenphysique = examenphysique;
    }

    public String getConduiteatenir() {
        return conduiteatenir;
    }

    public void setConduiteatenir(String conduiteatenir) {
        this.conduiteatenir = conduiteatenir;
    }

    public String getDiagnosticretenu() {
        return diagnosticretenu;
    }

    public void setDiagnosticretenu(String diagnosticretenu) {
        this.diagnosticretenu = diagnosticretenu;
    }

    public int getHospitalisation() {
        return hospitalisation;
    }

    public void setHospitalisation(int hospitalisation) {
        this.hospitalisation = hospitalisation;
    }

    public String getSoins() {
        return soins;
    }

    public void setSoins(String soins) {
        this.soins = soins;
    }

    public String getAvisconfrere() {
        return avisconfrere;
    }

    public void setAvisconfrere(String avisconfrere) {
        this.avisconfrere = avisconfrere;
    }

    public String getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(String ordonnance) {
        this.ordonnance = ordonnance;
    }

    public String getRenseignementsclin() {
        return renseignementsclin;
    }

    public void setRenseignementsclin(String renseignementsclin) {
        this.renseignementsclin = renseignementsclin;
    }
}

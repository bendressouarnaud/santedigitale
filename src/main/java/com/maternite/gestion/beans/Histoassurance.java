package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "histoassurance")
public class Histoassurance {

    @Id
    @Column(name = "idhas")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idhas;

    @Column(name = "assurance")
    private String assurance;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "sociale")
    private String sociale;

    @Column(name = "societe")
    private String societe;

    @Column(name = "particulier")
    private int particulier;

    @Column(name = "idpat")
    private int idpat;

    public Histoassurance() {
    }

    public int getIdhas() {
        return idhas;
    }

    public void setIdhas(int idhas) {
        this.idhas = idhas;
    }

    public String getAssurance() {
        return assurance;
    }

    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getSociale() {
        return sociale;
    }

    public void setSociale(String sociale) {
        this.sociale = sociale;
    }

    public int getParticulier() {
        return particulier;
    }

    public void setParticulier(int particulier) {
        this.particulier = particulier;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }
}

package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_patient_recours")
public class Patientrecours {

    @Id
    @Column(name = "idptr")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idptr;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "nomprenom")
    private String nomprenom;

    @Column(name = "adressepostale")
    private String adressepostale;

    @Column(name = "telephone")
    private String telephone;

    public Patientrecours() {
    }

    public int getIdptr() {
        return idptr;
    }

    public void setIdptr(int idptr) {
        this.idptr = idptr;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public String getNomprenom() {
        return nomprenom;
    }

    public void setNomprenom(String nomprenom) {
        this.nomprenom = nomprenom;
    }

    public String getAdressepostale() {
        return adressepostale;
    }

    public void setAdressepostale(String adressepostale) {
        this.adressepostale = adressepostale;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

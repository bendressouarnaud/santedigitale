package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_cmdexamens")
public class Cmdexamens {

    @Id
    @Column(name = "idcmx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcmx;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "idcon")
    private int idcon;

    @Column(name = "idexam")
    private int idexam;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "statut")
    private int statut;

    @Column(name = "dates")
    private Date dates;

    @Lob
    @Column(name = "fichier", columnDefinition = "MEDIUMBLOB")
    private byte[] fichier;

    @Column(name = "typefichier")
    private String typefichier;

    @Column(name = "nomfichier")
    private String nomfichier;

    @Column(name = "taillefichier")
    private long taillefichier;

    @Column(name = "date_fichier")
    private Date date_fichier;

    public Cmdexamens() {
    }

    public int getIdcmx() {
        return idcmx;
    }

    public void setIdcmx(int idcmx) {
        this.idcmx = idcmx;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }

    public int getIdexam() {
        return idexam;
    }

    public void setIdexam(int idexam) {
        this.idexam = idexam;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public byte[] getFichier() {
        return fichier;
    }

    public void setFichier(byte[] fichier) {
        this.fichier = fichier;
    }

    public String getTypefichier() {
        return typefichier;
    }

    public void setTypefichier(String typefichier) {
        this.typefichier = typefichier;
    }

    public String getNomfichier() {
        return nomfichier;
    }

    public void setNomfichier(String nomfichier) {
        this.nomfichier = nomfichier;
    }

    public long getTaillefichier() {
        return taillefichier;
    }

    public void setTaillefichier(long taillefichier) {
        this.taillefichier = taillefichier;
    }

    public Date getDate_fichier() {
        return date_fichier;
    }

    public void setDate_fichier(Date date_fichier) {
        this.date_fichier = date_fichier;
    }
}

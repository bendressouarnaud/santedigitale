package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_fichiers")
public class Fichiers {

    @Id
    @Column(name = "idfic")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idfic;

    @Column(name = "idcon")
    private int idcon;

    @Lob
    @Column(name = "fichier", columnDefinition = "MEDIUMBLOB")
    private byte[] fichier;

    @Column(name = "typefichier")
    private String typefichier;

    @Column(name = "nomfichier")
    private String nomfichier;

    @Column(name = "taillefichier")
    private long taillefichier;

    @Column(name = "idntf")
    private int idntf;

    public Fichiers() {
    }

    public int getIdfic() {
        return idfic;
    }

    public void setIdfic(int idfic) {
        this.idfic = idfic;
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

    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }

    public int getIdntf() {
        return idntf;
    }

    public void setIdntf(int idntf) {
        this.idntf = idntf;
    }
}

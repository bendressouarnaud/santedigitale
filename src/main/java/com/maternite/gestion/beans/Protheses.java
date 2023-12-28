package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_protheses")
public class Protheses {

    // Attributes :
    @Id
    @Column(name = "idpro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpro;

    @Column(name = "idcde")
    private int idcde;

    @Column(name = "idpat")
    private int idpat;

    @Column(name = "enplace")
    private String enplace;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "typeproth")
    private String typeproth;

    @Column(name = "dateprothese")
    private Date dateprothese;

    public Protheses() {
    }

    public int getIdpro() {
        return idpro;
    }

    public void setIdpro(int idpro) {
        this.idpro = idpro;
    }

    public int getIdcde() {
        return idcde;
    }

    public void setIdcde(int idcde) {
        this.idcde = idcde;
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public String getEnplace() {
        return enplace;
    }

    public void setEnplace(String enplace) {
        this.enplace = enplace;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getTypeproth() {
        return typeproth;
    }

    public void setTypeproth(String typeproth) {
        this.typeproth = typeproth;
    }

    public Date getDateprothese() {
        return dateprothese;
    }

    public void setDateprothese(Date dateprothese) {
        this.dateprothese = dateprothese;
    }
}

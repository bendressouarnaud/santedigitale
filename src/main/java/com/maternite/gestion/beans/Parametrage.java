package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_parametreage")
public class Parametrage {

    @Id
    @Column(name = "idprm")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idprm;

    @Column(name = "envoimailsecretaire")
    private int envoimailsecretaire;

    @Column(name = "envoimailadmin")
    private int envoimailadmin;

    @Column(name = "envoimailmanager")
    private int envoimailmanager;

    @Column(name = "envoimailsuperadmin")
    private int envoimailsuperadmin;

    @Column(name = "envoimaillaborantin")
    private int envoimaillaborantin;

    public Parametrage() {
    }

    public int getIdprm() {
        return idprm;
    }

    public void setIdprm(int idprm) {
        this.idprm = idprm;
    }

    public int getEnvoimailsecretaire() {
        return envoimailsecretaire;
    }

    public void setEnvoimailsecretaire(int envoimailsecretaire) {
        this.envoimailsecretaire = envoimailsecretaire;
    }

    public int getEnvoimailadmin() {
        return envoimailadmin;
    }

    public void setEnvoimailadmin(int envoimailadmin) {
        this.envoimailadmin = envoimailadmin;
    }

    public int getEnvoimailmanager() {
        return envoimailmanager;
    }

    public void setEnvoimailmanager(int envoimailmanager) {
        this.envoimailmanager = envoimailmanager;
    }

    public int getEnvoimailsuperadmin() {
        return envoimailsuperadmin;
    }

    public void setEnvoimailsuperadmin(int envoimailsuperadmin) {
        this.envoimailsuperadmin = envoimailsuperadmin;
    }

    public int getEnvoimaillaborantin() {
        return envoimaillaborantin;
    }

    public void setEnvoimaillaborantin(int envoimaillaborantin) {
        this.envoimaillaborantin = envoimaillaborantin;
    }
}

package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_patient_reservation")
public class Patientreservation {

    @Id
    @Column(name = "idpre")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpre;

    @Column(name = "idpaf")
    private int idpaf;

    @Column(name = "dates")
    private Date dates;

    public Patientreservation() {
    }

    public int getIdpre() {
        return idpre;
    }

    public void setIdpre(int idpre) {
        this.idpre = idpre;
    }

    public int getIdpaf() {
        return idpaf;
    }

    public void setIdpaf(int idpaf) {
        this.idpaf = idpaf;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
}

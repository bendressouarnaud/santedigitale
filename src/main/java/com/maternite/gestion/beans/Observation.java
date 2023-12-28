package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_observation")
public class Observation {

    @Id
    @Column(name = "idobs")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idobs;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "idcon")
    private int idcon;

    public Observation() {
    }

    public int getIdobs() {
        return idobs;
    }

    public void setIdobs(int idobs) {
        this.idobs = idobs;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdcon() {
        return idcon;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }
}

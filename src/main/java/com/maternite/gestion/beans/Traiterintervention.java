package com.maternite.gestion.beans;

public class Traiterintervention {

    String dates, dent,commentaire;

    public Traiterintervention(String dates, String dent, String commentaire) {
        this.dates = dates;
        this.dent = dent;
        this.commentaire = commentaire;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getDent() {
        return dent;
    }

    public void setDent(String dent) {
        this.dent = dent;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}

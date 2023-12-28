package com.maternite.gestion.mesobjets;

import java.util.ArrayList;
import java.util.List;

public class Commentaires {

    private String commentaire;
    private int limiteCaractere;
    private List<String> listeCommentaire;

    public Commentaires() {
        this.listeCommentaire = new ArrayList<String>();
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public List<String> getListeCommentaire() {
        return listeCommentaire;
    }

    public void setListeCommentaire(List<String> listeCommentaire) {
        this.listeCommentaire = listeCommentaire;
    }

    public int getLimiteCaractere() {
        return limiteCaractere;
    }

    public void setLimiteCaractere(int limiteCaractere) {
        this.limiteCaractere = limiteCaractere;
    }
}

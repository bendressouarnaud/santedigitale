package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_type")
public class Typesmedic {

    // Attributes :
    @Id
    @Column(name = "idtyp")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtyp;

    @Column(name = "libelle")
    private String libelle;

    public Typesmedic() {
    }

    public int getIdtyp() {
        return idtyp;
    }

    public void setIdtyp(int idtyp) {
        this.idtyp = idtyp;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

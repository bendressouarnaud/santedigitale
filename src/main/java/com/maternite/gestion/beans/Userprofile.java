package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_userprofile")
public class Userprofile {

    @Id
    @Column(name = "idprof")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idprof;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "profil")
    private String profil;

    @Column(name = "code")
    private int code;

    // Setters and Getters :
    public Long getIdprof() { return idprof;    }
    public void setIdprof(Long idprof) { this.idprof = idprof;    }

    public String getLibelle() { return libelle;    }
    public void setLibelle(String libelle) { this.libelle = libelle;    }

    public int getCode() { return code;    }
    public void setCode(int code) { this.code = code;    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
}

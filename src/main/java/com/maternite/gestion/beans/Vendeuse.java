package com.maternite.gestion.beans;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_vendeuse")
public class Vendeuse {

    @Id
    @Column(name = "idven")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idven;

    @Column(name = "nom")
    private String nom;

    @Column(name = "contact")
    private String contact;

    @Column(name = "email")
    private String email;

    @Column(name = "date_naissance")
    private Date datenaissance;

    @Column(name = "pieceidentite")
    private String pieceidentite;

    @Column(name = "sexe")
    private int sexe;

    @Column(name = "idmar")
    private int idmar;


    // Setters and Getters :
    public int getIdven() { return idven;    }
    public void setIdven(int idven) { this.idven = idven;    }

    public String getNom() { return nom;    }
    public void setNom(String nom) { this.nom = nom;    }

    public String getContact() { return contact;    }
    public void setContact(String contact) { this.contact = contact;    }

    public String getEmail() { return email;    }
    public void setEmail(String email) { this.email = email;    }

    public Date getDatenaissance() { return datenaissance;    }
    public void setDatenaissance(Date datenaissance) { this.datenaissance = datenaissance;    }

    public String getPieceidentite() { return pieceidentite;    }
    public void setPieceidentite(String pieceidentite) { this.pieceidentite = pieceidentite;    }

    public int getSexe() { return sexe;    }
    public void setSexe(int sexe) { this.sexe = sexe;    }

    public int getIdmar() { return idmar;    }
    public void setIdmar(int idmar) { this.idmar = idmar;    }

    public Vendeuse(){}

}

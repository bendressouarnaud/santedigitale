package com.maternite.gestion.beans;


import javax.persistence.*;

@Entity(name = "anf_agent")
public class Agent {

    @Id
    @Column(name = "idage")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idage;

    @Column(name = "nom")
    private String nom;

    @Column(name = "contact")
    private String contact;

    @Column(name = "identifiant")
    private String identifiant;

    @Column(name = "password")
    private String password;

    @Column(name = "idcom")
    private int idcom;


    // Setters and Getters :
    public int getIdage() { return idage;    }
    public void setIdage(int idage) { this.idage = idage;    }

    public String getNom() { return nom;    }
    public void setNom(String nom) { this.nom = nom;    }

    public String getContact() { return contact;    }
    public void setContact(String contact) { this.contact = contact;    }

    public String getIdentifiant() { return identifiant;    }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant;    }

    public String getPassword() { return password;    }
    public void setPassword(String password) { this.password = password;    }

    public int getIdcom() { return idcom;    }
    public void setIdcom(int idcom) { this.idcom = idcom;    }

    public Agent(){}

}

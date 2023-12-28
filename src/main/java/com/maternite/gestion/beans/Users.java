package com.maternite.gestion.beans;


import javax.persistence.*;
import java.util.Collection;

@Entity(name = "anf_users")
@NamedQuery(name = "Users.findByIdentifiantAndPassword",
        query = "select u from anf_users u where u.identifiant = ?1 and u.password = ?2")
public class Users {

    @Id
    @Column(name = "idusr")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusr;

    @Column(name = "nom")
    private String nom;

    @Column(name = "contact")
    private String contact;

    @Column(name = "identifiant")
    private String identifiant;

    @Column(name = "password")
    private String password;

    @Column(name = "idprof")
    private int idprof;

    @Column(name = "idhop")
    private int idhop;

    /*
    @OneToMany(mappedBy = "users")
    private Collection<Declaration> declarations;
    */

    // Setters and Getters :
    public Long getIdusr() { return idusr;    }
    public void setIdusr(Long idusr) { this.idusr = idusr;    }

    public String getNom() { return nom;    }
    public void setNom(String nom) { this.nom = nom;    }

    public String getContact() { return contact;    }
    public void setContact(String contact) { this.contact = contact;    }

    public String getIdentifiant() { return identifiant;    }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant;    }

    public String getPassword() { return password;    }
    public void setPassword(String password) { this.password = password;    }

    public int getIdprof() { return idprof;    }
    public void setIdprof(int idprof) { this.idprof = idprof;    }

    public int getIdhop() { return idhop;    }
    public void setIdhop(int idhop) { this.idhop = idhop;    }

    public Users(){}
}

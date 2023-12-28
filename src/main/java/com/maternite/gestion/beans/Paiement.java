package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_paiement")
public class Paiement {

    @Id
    @Column(name = "idpaie")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpaie;
    @Column(name = "dates")
    private Date dates;
    @Column(name = "heure")
    private String heure;
    @Column(name = "idper")
    private int idper;
    @Column(name = "montant")
    private int montant;
    @Column(name = "idven")
    private int idven;
    @Column(name = "idtaxe")
    private int idtaxe;
    @Column(name = "idage")
    private int idage;

    // M E T H O D S :
    public int getIdpaie() { return idpaie;    }
    public void setIdpaie(int idpaie) { this.idpaie = idpaie;    }
    public Date getDates() { return dates;    }
    public void setDates(Date dates) { this.dates = dates;    }
    public String getHeure() { return heure;    }
    public void setHeure(String heure) { this.heure = heure;    }

    // idper
    public int getIdper() { return idper;    }
    public void setIdper(int idper) { this.idper = idper;    }
    public int getMontant() { return montant;    }
    public void setMontant(int montant) { this.montant = montant;    }
    public int getIdven() { return idven;    }
    public void setIdven(int idven) { this.idven = idven;    }
    public int getIdtaxe() { return idtaxe;    }
    public void setIdtaxe(int idtaxe) { this.idtaxe = idtaxe;    }
    public int getIdage() { return idage;    }
    public void setIdage(int idage) { this.idage = idage;    }

    public Paiement(){}

}

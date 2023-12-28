package com.maternite.gestion.beans;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_declaration")
@NamedQuery(name = "Declaration.findByIddec",
        query = "select u from anf_declaration u where u.iddec = ?1")
public class Declaration {

    @Id
    @Column(name = "iddec")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddec;
    //private Long iddec;

    @Column(name = "nombebe")
    private String nombebe;
    @Column(name = "prenbebe")
    private String prenbebe;
    @Column(name = "sexe")
    private int sexe;
    @Column(name = "lieunaissance")
    private int lieunaissance;

    @Column(name = "pere")
    private String pere;

    @Temporal(TemporalType.DATE)
    @Column(name = "datenaisspere")
    private Date datenaisspere;

    @Column(name = "villenaisspere")
    private int villenaisspere;
    @Column(name = "professionpere")
    private int professionpere;
    @Column(name = "domicilepere")
    private String domicilepere;

    @Temporal(TemporalType.DATE)
    @Column(name = "datenaissmere")
    private Date datenaissmere;

    @Column(name = "villenaissmere")
    private int villenaissmere;
    @Column(name = "professionmere")
    private int professionmere;
    @Column(name = "domicilemere")
    private String domicilemere;
    @Column(name = "declarationde")
    private String declarationde;
    @Column(name = "recuelangue")
    private int recuelangue;
    @Column(name = "assistancede")
    private String assistancede;



    @Column(name = "nationperer")
    private int nationperer;
    @Column(name = "mere")
    private String mere;
    @Column(name = "nationmere")
    private int nationmere;

    @Temporal(TemporalType.DATE)
    @Column(name = "dates")
    private Date dates;

    @Column(name = "heure")
    private String heure;
    @Column(name = "etat")
    private int etat;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation")
    private Date creation;

    @Column(name = "idusr")
    private int idusr;

    @Lob
    @Column(name = "fichier", columnDefinition = "BLOB")
    private byte[] fichier;

    @Column(name = "typefichier")
    private String typefichier;

    @Column(name = "nomfichier")
    private String nomfichier;

    @Column(name = "taillefichier")
    private long taillefichier;


    /*@ManyToOne
    private Users users;*/

    public Declaration(){}

    // Setters and Getters :
    public String getMere() { return mere;    }
    public void setMere(String mere) { this.mere = mere;    }
    public int getNationmere() { return nationmere;    }
    public void setNationmere(int nationmere) { this.nationmere = nationmere;    }
    public Date getDates() { return dates;    }
    public void setDates(Date dates) { this.dates = dates;    }
    public String getHeure() { return heure;    }
    public void setHeure(String heure) { this.heure = heure;    }
    public int getEtat() { return etat;    }
    public void setEtat(int etat) { this.etat = etat;    }
    public int getSexe() { return sexe;    }
    public void setSexe(int sexe) { this.sexe = sexe;    }
    public Date getCreation() { return creation;    }
    public void setCreation(Date creation) { this.creation = creation;    }
    public int getIdusr() { return idusr;    }
    public void setIdusr(int idusr) { this.idusr = idusr;    }

    public int getIddec() { return iddec;    }
    public void setIddec(int iddec) { this.iddec = iddec;    }
    public String getNombebe() { return nombebe;    }
    public void setNombebe(String nombebe) { this.nombebe = nombebe;    }
    public String getPrenbebe() { return prenbebe;    }
    public void setPrenbebe(String prenbebe) { this.prenbebe = prenbebe;    }
    public String getPere() { return pere;    }
    public void setPere(String pere) { this.pere = pere;    }
    public int getNationperer() { return nationperer;    }
    public void setNationperer(int nationperer) { this.nationperer = nationperer;    }


    public Date getDatenaisspere() { return datenaisspere;    }
    public void setDatenaisspere(Date datenaisspere) { this.datenaisspere = datenaisspere;    }
    public int getVillenaisspere() { return villenaisspere;    }
    public void setVillenaisspere(int villenaisspere) { this.villenaisspere = villenaisspere;    }
    public int getProfessionpere() { return professionpere;    }
    public void setProfessionpere(int professionpere) { this.professionpere = professionpere;    }
    public String getDomicilepere() { return domicilepere;    }
    public void setDomicilepere(String domicilepere) { this.domicilepere = domicilepere;    }

    public Date getDatenaissmere() { return datenaissmere;    }
    public void setDatenaissmere(Date datenaissmere) { this.datenaissmere = datenaissmere;    }
    public int getVillenaissmere() { return villenaissmere;    }
    public void setVillenaissmere(int villenaissmere) { this.villenaissmere = villenaissmere;    }
    public int getProfessionmere() { return professionmere;    }
    public void setProfessionmere(int professionmere) { this.professionmere = professionmere;    }
    public String getDomicilemere() { return domicilemere;    }
    public void setDomicilemere(String domicilemere) { this.domicilemere = domicilemere;    }

    public String getDeclarationde() { return declarationde;    }
    public void setDeclarationde(String declarationde) { this.declarationde = declarationde;    }
    public int getRecuelangue() { return recuelangue;    }
    public void setRecuelangue(int recuelangue) { this.recuelangue = recuelangue;    }
    public String getAssistancede() { return assistancede;    }
    public void setAssistancede(String assistancede) { this.assistancede = assistancede;    }

    public int getLieunaissance() { return lieunaissance;    }
    public void setLieunaissance(int lieunaissance) { this.lieunaissance = lieunaissance;    }

    public byte[] getFichier() {
        return fichier;
    }

    public void setFichier(byte[] fichier) {
        this.fichier = fichier;
    }

    public String getTypefichier() {
        return typefichier;
    }

    public void setTypefichier(String typefichier) {
        this.typefichier = typefichier;
    }

    public String getNomfichier() {
        return nomfichier;
    }

    public void setNomfichier(String nomfichier) {
        this.nomfichier = nomfichier;
    }

    public long getTaillefichier() {
        return taillefichier;
    }

    public void setTaillefichier(long taillefichier) {
        this.taillefichier = taillefichier;
    }
}

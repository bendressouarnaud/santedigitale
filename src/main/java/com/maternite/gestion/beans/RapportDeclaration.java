package com.maternite.gestion.beans;

public class RapportDeclaration {

    // Attributes :
    public String bebe,sexe ,pere,mere,dates,heure,dateenreg,
        heureenreg, lieunaissance, villpere, profpere, domicilepere, natpere, villmere,
        profmere, domicilemere, natmere, declarationde, langue, assistancede, sagefemme,
            datenaisspere, datenaissmere;

    // Methods :
    public RapportDeclaration(){}
    public RapportDeclaration(String bebe,String sexe ,String pere,
                              String mere,String dates,String heure,String dateenreg,
                              String heureenreg, String lieunaissance, String villpere, String profpere,
                              String domicilepere, String natpere, String villmere,String profmere,
                              String domicilemere, String natmere, String declarationde, String langue,
                              String assistancede, String sagefemme,
                              String datenaisspere, String datenaissmere){
        this.bebe=bebe;
        this.sexe=sexe;
        this.pere=pere;
        this.mere=mere;
        this.dates=dates;
        this.heure=heure;

        this.dateenreg=dateenreg;
        this.heureenreg=heureenreg;
        this.lieunaissance=lieunaissance;
        this.villpere=villpere;
        this.profpere=profpere;
        this.domicilepere=domicilepere;
        this.natpere=natpere;
        this.villmere=villmere;
        this.profmere=profmere;
        this.domicilemere=domicilemere;
        this.natmere=natmere;
        this.declarationde=declarationde;
        this.langue=langue;
        this.assistancede=assistancede;
        this.sagefemme=sagefemme;

        this.datenaissmere = datenaissmere;
        this.datenaisspere = datenaisspere;
    }

    public String getDatenaissmere() { return datenaissmere;    }
    public String getDatenaisspere() { return datenaisspere;    }

    public String getBebe() { return bebe;    }
    public String getSexe() { return sexe;    }
    public String getPere() { return pere;    }
    public String getMere() { return mere;    }
    public String getDates() { return dates;    }
    public String getHeure() { return heure;    }
    public String getDateenreg() { return dateenreg;    }

    public String getHeureenreg() { return heureenreg;    }
    public String getLieunaissance() { return lieunaissance;    }
    public String getVillpere() { return villpere;    }
    public String getProfpere() { return profpere;    }
    public String getDomicilepere() { return domicilepere;    }
    public String getNatpere() { return natpere;    }
    public String getVillmere() { return villmere;    }
    public String getProfmere() { return profmere;    }
    public String getDomicilemere() { return domicilemere;    }
    public String getNatmere() { return natmere;    }
    public String getDeclarationde() { return declarationde;    }
    public String getLangue() { return langue;    }
    public String getAssistancede() { return assistancede;    }
    public String getSagefemme() { return sagefemme;    }
}

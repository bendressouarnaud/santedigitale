package com.maternite.gestion.beans;

import javax.persistence.*;

@Entity(name = "anf_upload")
public class UploadFichier {

    @Id
    @Column(name = "idupl")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idupl;

    @Column(name = "iddec")
    private int iddec;

    @Column(name = "chemin")
    private String chemin;

    // METHODS :
    public UploadFichier(){}

    public String getChemin() { return chemin;    }
    public void setChemin(String chemin) { this.chemin = chemin;    }
    public int getIddec() { return iddec;    }
    public void setIddec(int iddec) { this.iddec = iddec;    }
    public int getIdupl() { return idupl;    }
    public void setIdupl(int idupl) { this.idupl = idupl;    }

}

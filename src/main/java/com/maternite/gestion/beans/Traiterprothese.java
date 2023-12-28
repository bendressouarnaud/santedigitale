package com.maternite.gestion.beans;

public class Traiterprothese {

    String enplace, localisation, types, dates;

    public Traiterprothese(String enplace, String localisation, String types, String dates) {
        this.enplace = enplace;
        this.localisation = localisation;
        this.types = types;
        this.dates = dates;
    }

    public String getEnplace() {
        return enplace;
    }

    public void setEnplace(String enplace) {
        this.enplace = enplace;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
}

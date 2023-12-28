package com.maternite.gestion.mesobjets;

public class StatConsultationMois {

    String services, mois;
    long total;
    int idservices;

    public StatConsultationMois() {
    }

    public String getServices() {
        return services;
    }

    public int getIdservices() {
        return idservices;
    }

    public void setIdservices(int idservices) {
        this.idservices = idservices;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}

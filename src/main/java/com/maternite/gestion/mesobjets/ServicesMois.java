package com.maternite.gestion.mesobjets;

public class ServicesMois {

    String libservice;
    int services, mois;
    long total;

    public ServicesMois() {
    }

    public int getServices() {
        return services;
    }

    public void setServices(int services) {
        this.services = services;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getLibservice() {
        return libservice;
    }

    public void setLibservice(String libservice) {
        this.libservice = libservice;
    }
}

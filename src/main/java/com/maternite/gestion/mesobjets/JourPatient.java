package com.maternite.gestion.mesobjets;

public class JourPatient {

    String jour;
    long nombre;

    public JourPatient(String jour, long nombre) {
        this.jour = jour;
        this.nombre = nombre;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public long getNombre() {
        return nombre;
    }

    public void setNombre(long nombre) {
        this.nombre = nombre;
    }
}

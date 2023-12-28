package com.maternite.gestion.beans;

public class Donneesobservations {

    // Attributes :
    public String dates, consultation;

    public Donneesobservations() {
    }

    public Donneesobservations(String dates, String consultation) {
        this.dates = dates;
        this.consultation = consultation;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getConsultation() {
        return consultation;
    }

    public void setConsultation(String consultation) {
        this.consultation = consultation;
    }
}

package com.maternite.gestion.beans;

public class PatientPersonne {
    Patient patient;
    Patientrecours patientrecours;

    public PatientPersonne() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patientrecours getPatientrecours() {
        return patientrecours;
    }

    public void setPatientrecours(Patientrecours patientrecours) {
        this.patientrecours = patientrecours;
    }
}

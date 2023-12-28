package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Patientrecours;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientrecoursRepository extends
    CrudRepository<Patientrecours, Integer> {

    List<Patientrecours> findAllByIdpat(int idpat);
    Patientrecours findByIdpat(int idpat);
    Patientrecours findByIdptr(int idptr);
    List<Patientrecours> findAllByTelephone(String telephone);
    //
    Patientrecours findTopByIdpat(int idpat);
}

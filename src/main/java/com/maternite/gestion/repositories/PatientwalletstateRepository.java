package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Patientwalletstate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientwalletstateRepository extends
        CrudRepository<Patientwalletstate, Integer> {
    //
    List<Patientwalletstate> findAll();
    //
    List<Patientwalletstate> findAllByIdpat(int idpat);
    Patientwalletstate findByIdpat(int idpat);
}

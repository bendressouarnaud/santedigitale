package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Patientreservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PatientreservationRepository extends CrudRepository<Patientreservation,
        Integer> {
    //
    List<Patientreservation> findAll();
    Patientreservation findByIdpre(int idpre);
    Patientreservation findByIdpaf(int idpaf);

    // Delete :
    @Transactional
    void deleteByIdpaf(int idpaf);

    @Transactional
    void deleteByIdpre(int idpre);

}

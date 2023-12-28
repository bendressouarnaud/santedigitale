package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Patientfamille;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PatientfamilleRepository
        extends CrudRepository<Patientfamille, Integer> {

    //
    List<Patientfamille> findAll();
    //
    List<Patientfamille> findAllByIdparent(int idparent);
    Patientfamille findByIdpaf(int idpaf);

    // Delete :
    @Transactional
    void deleteByIdpaf(int idpaf);

}

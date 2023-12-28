package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Observation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ObservationRepository extends CrudRepository<Observation, Integer> {

    // Get All users :
    List<Observation> findAll();

    // Get DOCTOR by his ID and Pwd :
    Observation findByIdobs(int idobs);

    // Get using CONSULTATION Id :
    Observation findByIdcon(int idcon);

    //
    List<Observation> findAllByIdcon(int idcon);

    //
    @Transactional
    void deleteByIdcon(int idcon);
    void deleteAllByIdcon(int idcon);

}


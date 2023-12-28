package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Interventions;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface InterventionsRepository extends CrudRepository<Interventions, Integer> {
    List<Interventions> findAllByIdcon(int idcon);
    List<Interventions> findAllByIdpat(int idpat);
    Interventions findByIdcon(int idcon);
    Interventions findByIdconAndDates(int idcon, Date dates);
}

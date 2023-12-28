package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Assurance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssuranceRepository extends CrudRepository<Assurance, Integer> {

    List<Assurance> findAll();
    List<Assurance> findAllByActif(int actif);
    Assurance findByIdass(int idass);
}

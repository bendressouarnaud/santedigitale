package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Serviceplus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceplusRepository extends
        CrudRepository<Serviceplus, Integer> {

    //
    List<Serviceplus> findAll();

    //
    Serviceplus findByLibelle(String libelle);

}

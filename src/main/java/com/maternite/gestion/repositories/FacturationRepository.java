package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Facturation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FacturationRepository extends CrudRepository<Facturation, Integer> {

    List<Facturation> findAll();
    Facturation findByIdpat(int idpat);
    Facturation findByIdfac(int idfac);

    Facturation findTopByIdpat(int idpat);

}

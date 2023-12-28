package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Commune;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommuneRepository extends CrudRepository<Commune, Integer> {
    List<Commune> findAll();
    Commune findByIdcom(@Param("idcom") int idcom);
}
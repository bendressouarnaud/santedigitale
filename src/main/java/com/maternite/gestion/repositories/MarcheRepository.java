package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Marche;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarcheRepository extends CrudRepository<Marche, Integer> {
    List<Marche> findAll();
    Marche findByIdcom(@Param("idmar") int idmar);
}

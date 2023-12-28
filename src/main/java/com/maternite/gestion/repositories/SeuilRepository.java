package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Seuil;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeuilRepository extends CrudRepository<Seuil, Integer> {

    List<Seuil> findAll();
    Seuil findTopByOrderByIdseuDesc();

}

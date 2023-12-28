package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Modedevie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModedevieRepository extends CrudRepository<Modedevie, Integer> {

    List<Modedevie> findAll();
    Modedevie findByIdmvie(int idmvie);

}

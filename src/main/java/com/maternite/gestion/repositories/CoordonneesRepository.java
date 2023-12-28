package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Coordonnees;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoordonneesRepository extends CrudRepository<Coordonnees, Integer> {

    List<Coordonnees> findAll();

    Coordonnees findByIdcord(int idcord);

}

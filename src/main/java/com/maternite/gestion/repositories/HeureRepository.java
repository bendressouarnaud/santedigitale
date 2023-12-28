package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Heure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeureRepository extends CrudRepository<Heure, Integer> {
    List<Heure> findAll();

    Heure findByLibelle(String heure);

    Heure findByIdheu(int idheu);
}

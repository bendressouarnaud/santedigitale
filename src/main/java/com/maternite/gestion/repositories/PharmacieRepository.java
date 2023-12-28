package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Pharmacie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PharmacieRepository extends CrudRepository<Pharmacie, Integer> {

    List<Pharmacie> findAll();
    List<Pharmacie> findAllByIdpharmGreaterThan(int idpharm);
    Pharmacie findByIdpharm(int idpharm);
    List<Pharmacie> findAllByLibelle(String libelle);
}

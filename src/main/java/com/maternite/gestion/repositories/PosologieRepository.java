package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Posologie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PosologieRepository extends CrudRepository<Posologie, Integer> {

    // Get All users :
    List<Posologie> findAllByOrderByLibelleAsc();

    // Get DOCTOR by his ID and Pwd :
    Posologie findByIdpos(int idpos);
    Posologie findByLibelle(String libelle);

}

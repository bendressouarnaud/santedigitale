package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Quantite;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuantiteRepository extends CrudRepository<Quantite, Integer> {

    // Get All users :
    List<Quantite> findAllByOrderByLibelleAsc();

    // Get DOCTOR by his ID and Pwd :
    Quantite findByIdqte(int idqte);
    Quantite findByLibelle(String libelle);

}

package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Paiement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaiementRepository  extends CrudRepository<Paiement, Integer> {

    // Get All users :
    List<Paiement> findAll();
    Paiement findByIdpaie(@Param("idpaie") int idpaie);
}
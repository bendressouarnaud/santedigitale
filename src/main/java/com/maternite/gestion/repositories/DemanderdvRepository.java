package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Demanderdv;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DemanderdvRepository extends CrudRepository<Demanderdv, Integer> {

    List<Demanderdv> findAll();

    Demanderdv findByIdddv(int idddv);
    Demanderdv findByIdserAndEmail(int idser, String email);
    Demanderdv findByIdserAndIdpat(int idser, int idpat);
    Demanderdv findByIdserAndIdpatAndIdmed(int idser, int idpat, int idmed);

    // Delete :
    @Transactional
    void deleteByIdddv(int idddv);
}

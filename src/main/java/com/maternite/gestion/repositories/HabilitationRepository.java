package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Habilitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HabilitationRepository extends CrudRepository<Habilitation, Integer> {

    // Get All :
    List<Habilitation> findAll();

    // Get One :
    Habilitation findByIdadminAndIdmed(int idadmin,int idmed);
    Habilitation findByIdhab(int Idhab);

    // Delete :
    @Transactional
    void deleteByIdhab(int Idhab);
}

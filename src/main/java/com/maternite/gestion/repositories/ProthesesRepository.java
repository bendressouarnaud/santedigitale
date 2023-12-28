package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Protheses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProthesesRepository extends CrudRepository<Protheses, Integer> {

    // Get ALL :
    List<Protheses> findAllByIdpat(int idpat);

    // Delete :
    @Transactional
    void deleteAllByIdpat(int idpat);

}

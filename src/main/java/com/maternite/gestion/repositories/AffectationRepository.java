package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Affectation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AffectationRepository extends CrudRepository<Affectation, Integer> {

    // Get All users :
    List<Affectation> findAll();

    // Get User by his ID and Pwd :
    Affectation findByIdmed(int idmed);
}
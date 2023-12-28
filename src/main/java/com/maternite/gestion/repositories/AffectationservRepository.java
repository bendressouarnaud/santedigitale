package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Affectationserv;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AffectationservRepository  extends CrudRepository<Affectationserv, Integer> {

    // Get All  :
    List<Affectationserv> findAll();
    List<Affectationserv> findAllByIdmed(int idmed);
    List<Affectationserv> findAllByIdser(int idser);

    // Get one :
    Affectationserv findByIdmedAndIdser(int idmed, int idser);
}

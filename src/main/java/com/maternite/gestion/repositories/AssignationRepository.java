package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Assignation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssignationRepository extends CrudRepository<Assignation, Integer> {

    // All :
    List<Assignation> findAll();
    Assignation findByIdmedAndIdhop(int idmed, int idhop);
    Assignation findByIdmed(int idmed);
    List<Assignation> findAllByIdmed(int idmed);

    List<Assignation> findAllByIdhopIn(List<Integer> liste);
}

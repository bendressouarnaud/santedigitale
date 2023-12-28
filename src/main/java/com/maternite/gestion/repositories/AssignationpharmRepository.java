package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Assignationpharm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssignationpharmRepository extends CrudRepository<Assignationpharm, Integer> {

    List<Assignationpharm> findAll();
    Assignationpharm findByIdmed(int idmed);
    Assignationpharm findByIdmedAndIdpharm(int idmed, int idpharm);

}

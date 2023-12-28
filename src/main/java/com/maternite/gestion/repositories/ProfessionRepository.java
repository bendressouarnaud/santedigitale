package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Profession;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfessionRepository extends CrudRepository<Profession, Integer> {

    // Get All users :
    List<Profession> findAllByOrderByLibelleAsc();

    // Get DOCTOR by his ID and Pwd :
    Profession findByIdprof(int idprof);
    List<Profession> findByLibelleIn(List<String> liste);
}

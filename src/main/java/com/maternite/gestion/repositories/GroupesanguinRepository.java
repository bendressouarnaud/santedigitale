package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Groupesanguin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupesanguinRepository extends CrudRepository<Groupesanguin, Integer> {

    List<Groupesanguin> findAllByOrderByLibelleAsc();
    Groupesanguin findByIdgsn(int idgsn);
}

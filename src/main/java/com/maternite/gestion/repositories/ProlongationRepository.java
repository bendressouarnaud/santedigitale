package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Prolongation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProlongationRepository extends
        CrudRepository<Prolongation, Integer> {

    Prolongation findByIdcon(int idcon);
    List<Prolongation> findAllByIdconOrderByIdproAsc(int idcon);

}

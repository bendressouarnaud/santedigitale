package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Approvisionnement;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ApprovisionnementRepository extends
        CrudRepository<Approvisionnement, Integer> {

    List<Approvisionnement> findAll();
    List<Approvisionnement> findAllByDates(Date date);

}

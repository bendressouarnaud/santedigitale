package com.maternite.gestion.repositories;


import com.maternite.gestion.beans.Hopital;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HopitalRepository extends CrudRepository<Hopital, Long> {

    List<Hopital> findAll();
    List<Hopital> findAllByIdhopIn(List<Long> idhop);
    Hopital findByIdhop(Long idhop);

}


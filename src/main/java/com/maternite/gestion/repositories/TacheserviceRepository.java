package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Tacheservice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TacheserviceRepository extends CrudRepository<Tacheservice, Integer> {

    //
    List<Tacheservice> findAll();

    //
    Tacheservice findByIdabo(int idabo);
    Tacheservice findByIdtac(int idtac);

}

package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Ventemedicament;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VentemedicamentRepository extends
        CrudRepository<Ventemedicament, Integer> {

    List<Ventemedicament> findAll();

}

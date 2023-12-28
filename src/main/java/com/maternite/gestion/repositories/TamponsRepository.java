package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Tampons;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TamponsRepository extends CrudRepository<Tampons, Integer> {

    // Get ALL :
    List<Tampons> findAll();
    List<Tampons> findAllByIdmed(int idmed);
    Tampons findTopByIdmed(int idmed);

}

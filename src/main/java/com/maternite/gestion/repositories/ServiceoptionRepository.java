package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Serviceoption;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceoptionRepository extends CrudRepository<Serviceoption, Integer> {

    // Get All users :
    List<Serviceoption> findAll();

    //
    Serviceoption findByIdser(int idser);
    Serviceoption findByIdserAndIdhop(int idser, int idhop);
    Serviceoption findByIdsop(int idsop);
}

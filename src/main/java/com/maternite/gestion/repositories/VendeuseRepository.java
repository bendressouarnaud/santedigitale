package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Vendeuse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendeuseRepository extends CrudRepository<Vendeuse, Integer> {

    // Get All users :
    List<Vendeuse> findAll();
    Vendeuse findByIdven(@Param("idven") int idven);
}
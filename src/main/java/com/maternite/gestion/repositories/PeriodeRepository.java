package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Periode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeriodeRepository  extends CrudRepository<Periode, Integer> {

    // Get All users :
    List<Periode> findAll();
    Periode findByIdper(@Param("idper") int idper);
}
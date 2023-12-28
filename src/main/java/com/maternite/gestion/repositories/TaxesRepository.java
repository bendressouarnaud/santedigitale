package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Taxes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaxesRepository  extends CrudRepository<Taxes, Integer> {

    // Get All users :
    List<Taxes> findAll();
    Taxes findByItaxe(@Param("itaxe") int itaxe);
}

package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Naturefichier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NaturefichierRepository extends CrudRepository<Naturefichier, Integer> {

    List<Naturefichier> findAll();

}

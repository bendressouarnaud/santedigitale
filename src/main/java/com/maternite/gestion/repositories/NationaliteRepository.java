package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Nationalite;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NationaliteRepository extends CrudRepository<Nationalite, Integer> {
    List<Nationalite> findAll();
}

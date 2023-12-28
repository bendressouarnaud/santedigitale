package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Services;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServicesRepository extends CrudRepository<Services, Integer> {

    // Get All users :
    List<Services> findAll();
    List<Services> findAllByIdserIn(List<Integer> idser);

    // Get DOCTOR by his ID and Pwd :
    Services findByIdser(int idser);
    Services findByLibelle(String libelle);
}

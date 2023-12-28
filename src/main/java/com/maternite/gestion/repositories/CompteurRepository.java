package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Compteur;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface CompteurRepository extends CrudRepository<Compteur, Integer> {

    // Get the DATE :
    Compteur findByDates(Date dates);

}

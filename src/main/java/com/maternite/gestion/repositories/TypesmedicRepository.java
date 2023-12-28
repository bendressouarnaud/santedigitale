package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Typesmedic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypesmedicRepository extends CrudRepository<Typesmedic, Integer> {

    // Get All users :
    List<Typesmedic> findAllByOrderByLibelleAsc();

    // Get DOCTOR by his ID and Pwd :
    Typesmedic findByIdtyp(int idtyp);
    Typesmedic findByLibelle(String libelle);

}

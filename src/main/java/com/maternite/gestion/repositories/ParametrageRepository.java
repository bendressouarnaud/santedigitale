package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Parametrage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParametrageRepository extends CrudRepository <Parametrage,
Integer>{
    //
    List<Parametrage> findAll();
    //
    Parametrage findByIdprm(int idprm);
}

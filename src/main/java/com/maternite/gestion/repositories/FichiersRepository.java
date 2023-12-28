package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Fichiers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FichiersRepository extends CrudRepository<Fichiers, Integer> {

    List<Fichiers> findAll();

    List<Fichiers> findAllByIdcon(int idcon);

    Fichiers findByIdfic(int idfic);

}

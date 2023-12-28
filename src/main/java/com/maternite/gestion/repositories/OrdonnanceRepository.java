package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Ordonnance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrdonnanceRepository extends CrudRepository<Ordonnance, Integer> {

    // Get All users :
    List<Ordonnance> findAll();

    // Get User by his ID and Pwd :
    Ordonnance findByIdord(int idord);

    List<Ordonnance> findAllByIdcon(int idcon);

    // Delete :
    @Transactional
    void deleteByIdcon(int idcon);
}

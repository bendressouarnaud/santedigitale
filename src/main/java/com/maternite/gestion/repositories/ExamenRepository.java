package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Examen;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExamenRepository extends CrudRepository<Examen, Integer> {

    // Get All users :
    List<Examen> findAll();

    // Get DOCTOR by his ID and Pwd :
    Examen findByIdexam(int idexam);
}

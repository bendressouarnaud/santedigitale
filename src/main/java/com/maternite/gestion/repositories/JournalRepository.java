package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Journal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JournalRepository extends CrudRepository<Journal, Integer> {
    List<Journal> findAll();
    Journal findByIdjou(@Param("idjou") int idjou);
    List<Journal> findAllByIdmed(@Param("idmed") int idmed);
    List<Journal> findAllByIdmedAndDatesBetween(int idmed, Date debut, Date fin);
    List<Journal> findAllByDatesBetween(Date debut,Date fin);
}

package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Abonner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AbonnerRepository extends CrudRepository <Abonner, Integer> {

    //
    List<Abonner> findAll();

    //
    Abonner findByIdpatAndIdsps(int idpat, int idsps);
    Abonner findByIdabo(int idabo);
}

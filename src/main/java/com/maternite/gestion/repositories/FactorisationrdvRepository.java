package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Factorisationrdv;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface FactorisationrdvRepository extends
        CrudRepository<Factorisationrdv, Integer> {

    //
    Factorisationrdv findByIdfdv(int idfdv);
    Factorisationrdv findByIdpatAndProvenanceAndIdmedAndDates(int idpat,
        int provenance, int idmed, Date dates);
    Factorisationrdv findTopByIdserAndDatesAndIdmedIn(int idser,
        Date dates, List<Integer> listeidmed);
    List<Factorisationrdv> findAllByIdser(int idser);
    List<Factorisationrdv> findAllByIdmed(int idmed);
}

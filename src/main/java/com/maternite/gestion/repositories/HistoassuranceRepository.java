package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Histoassurance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoassuranceRepository extends
        CrudRepository<Histoassurance, Integer> {

    List<Histoassurance> findAllByIdpat(int idpat);
    Histoassurance findTopByIdpat(int idpat);
    //Histoassurance fin
    Histoassurance findTopByIdpatOrderByIdhasDesc(int idpat);
}

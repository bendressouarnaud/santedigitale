package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Constante;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ConstanteRepository extends CrudRepository<Constante, Integer> {

    List<Constante> findAll();
    List<Constante> findAllByIdpat(int idpat);
    // --------------------------------------
    Constante findByIdcons(int idcons);
    Constante findByDates(Date dates);
    Constante findByDatesAndIdpat(Date dates, int idpat);
    Constante findByIdcon(int idcon);
    Constante findTopByIdpatAndDatesOrderByIdconsDesc(int idpat, Date dates);
}

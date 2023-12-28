package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Qcodes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QcodesRepository extends CrudRepository<Qcodes, Integer> {

    //
    List<Qcodes> findAll();
    Qcodes findByIdcon(int idcon);
}

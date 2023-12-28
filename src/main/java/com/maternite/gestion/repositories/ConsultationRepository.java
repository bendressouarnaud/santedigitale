package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Consultation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConsultationRepository extends CrudRepository<Consultation, Integer> {

    // Get All users :
    List<Consultation> findAll();
    List<Consultation> findAllByIdhop(int idhop);

    // Get DOCTOR by his ID and Pwd :
    Consultation findByIdcon(int idcon);
    Consultation findTopByIdpat(int idpat);

    //
    List<Consultation> findAllByIdpat(int idpat);
}

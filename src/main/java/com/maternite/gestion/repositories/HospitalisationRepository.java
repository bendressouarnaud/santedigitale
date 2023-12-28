package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Hospitalisation;
import org.springframework.data.repository.CrudRepository;

public interface HospitalisationRepository extends CrudRepository<Hospitalisation, Integer> {

    Hospitalisation findByIdcon(int idcon);

}

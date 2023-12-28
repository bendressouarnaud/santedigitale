package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Dosage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DosageRepository extends CrudRepository<Dosage, Integer> {

    // Get All users :
    List<Dosage> findAllByOrderByLibelleAsc();

    // Get DOCTOR by his ID and Pwd :
    Dosage findByIddos(int iddos);
    Dosage findByLibelle(String libelle);

}

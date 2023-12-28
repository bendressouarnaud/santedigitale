package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Medicament;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicamentRepository extends CrudRepository<Medicament, Integer> {

    List<Medicament> findAll();
    List<Medicament> findAllByOrderByLibelleAsc();
    List<Medicament> findByQuantiteLessThanEqual(int limite);
    Medicament findByIdmd(int idmd);
    Medicament findByLibelle(String libelle);

}

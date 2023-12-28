package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Patientwallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientwalletRepository extends
        CrudRepository<Patientwallet, Integer> {
    //
    List<Patientwallet> findAll();
    //
    Patientwallet findByIdpat(int idpat);
    Patientwallet findByIdpwa(int idpwa);
    //
    @Query(value = "select sum(a.montant) from anf_patient_wallet a where a.idpat = ?1",
        nativeQuery = true)
    public Long cotisation(int idpat);

}

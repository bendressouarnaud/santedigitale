package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

    // Get All users :
    List<Patient> findAll();

    // Get DOCTOR by his ID and Pwd :
    Patient findByIdpat(int idpat);

    // Get by IDENT :
    Patient findByIdentifiant(String Identifiant);
    Patient findByIdentifiantAndProvenance(String Identifiant, int provenance);
    Patient findByEmail(String email);
    Patient findByEmailAndIdentifiant(String email, String identifiant);
    Patient findByEmailAndProvenance(String email, int provenance);
    Patient findByNomAndPrenomAndTelephone(String nom, String prenom,
        String telephone);
}

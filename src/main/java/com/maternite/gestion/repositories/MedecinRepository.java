package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Medecin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedecinRepository extends CrudRepository<Medecin, Integer> {

    // Get All users :
    List<Medecin> findAll();
    //
    List<Medecin> findAllByProfil(String profil);
    List<Medecin> findAllByProfilIn(List<String> profil);

    //
    List<Medecin> findAllByProfilAndIdmedIn(String profil, List<Integer> idmed);
    List<Medecin> findAllByProfilInAndIdmedIn(List<String> profil,
        List<Integer> idmed);
    List<Medecin> findAllByIdmedIn(List<Integer> indmed);

    // Get DOCTOR by his ID and Pwd :
    Medecin findByIdentifiantAndMotpasse(String identifiant, String motpasse);
    Medecin findByIdentifiant(String identifiant);
    Medecin findByIdmed(int idmed);
    Medecin findByEmail(String email);

    // All profiles and not the current user connected :
    List<Medecin> findAllByProfilAndIdmedNotIn(String profil, List<Integer> roles);
}

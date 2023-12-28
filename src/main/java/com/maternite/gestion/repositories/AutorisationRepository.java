package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Autorisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AutorisationRepository extends CrudRepository<Autorisation, Integer> {

    // Get All users :
    List<Autorisation> findAll();

    // Get DOCTOR by his ID and Pwd :
    Autorisation findByIdaut(int idaut);

    // Get it :
    Autorisation findByIdmedoriginAndIdmeddestin(int idmedorigin, int idmeddestin);
    Autorisation findByIdmedoriginAndIdmeddestinAndIdpat(int idmedorigin,
                                                         int idmeddestin, int idpat);

    //
    List<Autorisation> findAllByIdmeddestin(int idmedorigin);

    // Delete :
    @Transactional
    void deleteByIdaut(int idaut);
}
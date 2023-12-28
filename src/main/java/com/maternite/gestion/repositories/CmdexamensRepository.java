package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Cmdexamens;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CmdexamensRepository extends CrudRepository<Cmdexamens, Integer> {

    // Get All users :
    List<Cmdexamens> findAll();
    List<Cmdexamens> findAllByIdcon(int idcon);

    // Find by Idpat
    List<Cmdexamens> findAllByIdpat(int idpat);
    Cmdexamens findByIdcmx(int idcmx);
    List<Cmdexamens> findAllByIdcmx(int idcmx);
    Cmdexamens findByIdcmxAndIdcon(int idcmx, int idcon);
    Cmdexamens findByIdexamAndIdcon(int idexam, int idcon);

    // Delete :
    @Transactional
    void deleteAllByIdconAndStatut(int idcon, int statut);
    @Transactional
    void deleteAllByIdconAndStatutAndMontant(int idcon, int statut, Double montant);
}

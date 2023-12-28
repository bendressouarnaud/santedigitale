package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Rendezvous;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RendezvousRepository extends CrudRepository<Rendezvous, Integer> {

    //
    List<Rendezvous> findAll();

    //
    Rendezvous findByIdcon(int idcon);
    Rendezvous findByIdrdv(int idrdv);

    // Delete :
    @Transactional
    void deleteByIdcon(int idcon);

}

package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.AnfCustomer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnfCustomerRepository extends
        CrudRepository<AnfCustomer, Integer> {

    AnfCustomer findByIdcus(int idcus);
    AnfCustomer findByNom(String nom);

    //
    List<AnfCustomer> findAll();

}

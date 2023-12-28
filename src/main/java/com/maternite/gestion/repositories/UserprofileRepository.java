package com.maternite.gestion.repositories;


import com.maternite.gestion.beans.Userprofile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserprofileRepository extends CrudRepository<Userprofile, Long> {

    List<Userprofile> findAll();
    List<Userprofile> findAllByOrderByLibelleAsc();

}
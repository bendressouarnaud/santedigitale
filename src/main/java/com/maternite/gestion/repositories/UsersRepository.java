package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository  extends CrudRepository<Users, Long> {

    // Get All users :
    List<Users> findAll();

    // Get User by his ID and Pwd :
    Users findByIdentifiantAndPassword(@Param("identifiant") String identifiant,
                                       @Param("paassword") String paassword);
}

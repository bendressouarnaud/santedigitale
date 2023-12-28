package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Agent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgentRepository extends CrudRepository<Agent, Integer> {

    // Get All users :
    List<Agent> findAll();

    // Get User by his ID and Pwd :
    Agent findByIdentifiantAndPassword(@Param("identifiant") String identifiant,
                                       @Param("password") String password);
}

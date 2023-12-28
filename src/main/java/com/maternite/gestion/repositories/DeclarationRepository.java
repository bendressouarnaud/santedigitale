package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Declaration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeclarationRepository extends CrudRepository<Declaration, Integer> {
    List<Declaration> findAll();

    Declaration findByIddec(@Param("iddec") int iddec);
}

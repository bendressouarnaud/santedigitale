package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.UploadFichier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UploadFichierRepository extends CrudRepository<UploadFichier, Integer> {
    List<UploadFichier> findAll();

    UploadFichier findByIddec(@Param("idupl") int iddec);
}

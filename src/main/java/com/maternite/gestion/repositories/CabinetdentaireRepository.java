package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.Cabinetdentaire;
import org.springframework.data.repository.CrudRepository;

public interface CabinetdentaireRepository extends CrudRepository<Cabinetdentaire, Integer> {
    Cabinetdentaire findByIdcon(int idcon);
    Cabinetdentaire findByIdpat(int idpat);
}

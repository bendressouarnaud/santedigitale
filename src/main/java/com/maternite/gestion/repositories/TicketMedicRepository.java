package com.maternite.gestion.repositories;

import com.maternite.gestion.beans.TicketMedic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketMedicRepository extends CrudRepository<TicketMedic, Integer> {

    List<TicketMedic> findAll();

}

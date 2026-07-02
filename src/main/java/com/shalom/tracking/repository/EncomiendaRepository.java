package com.shalom.tracking.repository;

import com.shalom.tracking.model.Encomienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomiendaRepository extends JpaRepository<Encomienda, Long> {
}

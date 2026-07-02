package com.shalom.tracking.repository;

import com.shalom.tracking.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
    Optional<Envio> findByCodSeguimiento(String codSeguimiento);
}

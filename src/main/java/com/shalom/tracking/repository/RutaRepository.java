package com.shalom.tracking.repository;

import com.shalom.tracking.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    Optional<Ruta> findByPlacaVehiculoAndAgenciaDestino(String placaVehiculo, String agenciaDestino);
}

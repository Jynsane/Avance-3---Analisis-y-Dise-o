package com.shalom.tracking.repository;

import com.shalom.tracking.model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
    List<Comprobante> findByDniRuc(String dniRuc);
}

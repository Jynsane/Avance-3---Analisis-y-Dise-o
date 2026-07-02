package com.shalom.tracking.service;

import com.shalom.tracking.model.Comprobante;
import com.shalom.tracking.model.Envio;
import com.shalom.tracking.repository.ComprobanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ComprobanteService {

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    public List<Comprobante> findAll() {
        return comprobanteRepository.findAll();
    }

    public Optional<Comprobante> findById(Long id) {
        return comprobanteRepository.findById(id);
    }

    public List<Comprobante> findByDniRuc(String dniRuc) {
        return comprobanteRepository.findByDniRuc(dniRuc);
    }

    public Comprobante save(Comprobante comprobante) {
        return comprobanteRepository.save(comprobante);
    }

    public Comprobante generateComprobante(Envio envio, String tipoDocumento) {
        Comprobante comprobante = new Comprobante();
        comprobante.setEnvio(envio);
        comprobante.setTipoDocumento(tipoDocumento);
        comprobante.setMontoTotal(envio.getCostoTotal());
        comprobante.setNombreCliente(envio.getCliente().getNombreCompleto());
        comprobante.setDniRuc(envio.getCliente().getDniRuc());
        comprobante.setFechaEmision(LocalDate.now());
        return comprobanteRepository.save(comprobante);
    }
}

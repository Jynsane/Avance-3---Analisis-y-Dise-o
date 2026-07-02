package com.shalom.tracking.service;

import com.shalom.tracking.model.Envio;
import com.shalom.tracking.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> findAll() {
        return envioRepository.findAll();
    }

    public Optional<Envio> findById(Long id) {
        return envioRepository.findById(id);
    }

    public Optional<Envio> findByCodSeguimiento(String codSeguimiento) {
        return envioRepository.findByCodSeguimiento(codSeguimiento);
    }

    public Envio save(Envio envio) {
        if (envio.getCodSeguimiento() == null || envio.getCodSeguimiento().isBlank()) {
            envio.setCodSeguimiento(generateTrackingNumber());
        }
        return envioRepository.save(envio);
    }

    public void deleteById(Long id) {
        envioRepository.deleteById(id);
    }

    public double calculateCosto(double pesoKg, String dimensiones) {
        // Tarifa base: 10.0 soles
        // 2.5 soles por kg
        // Si tiene dimensiones grandes (contiene números mayores a 50 o largo), agregar extra
        double costoBase = 12.00;
        double costoPeso = pesoKg * 3.50;
        double extraDim = 0.0;
        if (dimensiones != null && (dimensiones.toLowerCase().contains("x") || dimensiones.toLowerCase().contains("x"))) {
            String[] parts = dimensiones.toLowerCase().split("x");
            try {
                for (String part : parts) {
                    double val = Double.parseDouble(part.trim());
                    if (val > 40) {
                        extraDim += 10.0;
                    }
                }
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return Math.round((costoBase + costoPeso + extraDim) * 100.0) / 100.0;
    }

    private String generateTrackingNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(900000) + 100000;
        return "SH-" + number;
    }
}

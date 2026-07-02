package com.shalom.tracking.service;

import com.shalom.tracking.model.Encomienda;
import com.shalom.tracking.repository.EncomiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EncomiendaService {

    @Autowired
    private EncomiendaRepository encomiendaRepository;

    public List<Encomienda> findAll() {
        return encomiendaRepository.findAll();
    }

    public Optional<Encomienda> findById(Long id) {
        return encomiendaRepository.findById(id);
    }

    public Encomienda save(Encomienda encomienda) {
        return encomiendaRepository.save(encomienda);
    }

    public void deleteById(Long id) {
        encomiendaRepository.deleteById(id);
    }
}

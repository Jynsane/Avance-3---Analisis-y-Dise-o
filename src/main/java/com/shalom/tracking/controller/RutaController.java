package com.shalom.tracking.controller;

import com.shalom.tracking.model.Ruta;
import com.shalom.tracking.service.RutaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/rutas")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rutas", rutaService.findAll());
        return "rutas/list";
    }

    @GetMapping("/nueva")
    public String showForm(Model model) {
        model.addAttribute("ruta", new Ruta());
        return "rutas/form";
    }

    @PostMapping("/guardar")
    public String save(@Valid @ModelAttribute("ruta") Ruta ruta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rutas/form";
        }

        // Validar duplicado de placa y destino (CU05)
        if (ruta.getIdRuta() == null) {
            Optional<Ruta> existing = rutaService.findByPlacaAndAgencia(ruta.getPlacaVehiculo(), ruta.getAgenciaDestino());
            if (existing.isPresent()) {
                model.addAttribute("error", "Ya existe una ruta asignada para el vehículo " + ruta.getPlacaVehiculo() + " con destino a " + ruta.getAgenciaDestino());
                return "rutas/form";
            }
        }

        rutaService.save(ruta);
        return "redirect:/rutas";
    }

    @GetMapping("/editar/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Ruta> ruta = rutaService.findById(id);
        if (ruta.isPresent()) {
            model.addAttribute("ruta", ruta.get());
            return "rutas/form";
        }
        return "redirect:/rutas";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {
        rutaService.deleteById(id);
        return "redirect:/rutas";
    }
}

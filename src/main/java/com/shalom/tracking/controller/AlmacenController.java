package com.shalom.tracking.controller;

import com.shalom.tracking.model.Envio;
import com.shalom.tracking.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/almacen")
public class AlmacenController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("envios", envioService.findAll());
        return "almacen/list";
    }

    @GetMapping("/actualizar/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Envio> envio = envioService.findById(id);
        if (envio.isPresent()) {
            model.addAttribute("envio", envio.get());
            return "almacen/form";
        }
        return "redirect:/almacen";
    }

    @PostMapping("/guardar")
    public String updateStatus(@RequestParam Long idEnvio, @RequestParam String nuevoEstado, Model model) {
        Optional<Envio> envioOpt = envioService.findById(idEnvio);
        if (envioOpt.isPresent()) {
            Envio envio = envioOpt.get();
            envio.setEstadoPaquete(nuevoEstado);
            envio.getEncomienda().setEstado(nuevoEstado);
            envioService.save(envio);
        }
        return "redirect:/almacen";
    }
}

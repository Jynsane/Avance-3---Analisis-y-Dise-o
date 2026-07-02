package com.shalom.tracking.controller;

import com.shalom.tracking.model.Envio;
import com.shalom.tracking.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("envios", envioService.findAll());
        return "entregas/list";
    }

    @GetMapping("/registrar/{id}")
    public String showForm(@PathVariable Long id, Model model) {
        Optional<Envio> envio = envioService.findById(id);
        if (envio.isPresent()) {
            model.addAttribute("envio", envio.get());
            return "entregas/form";
        }
        return "redirect:/entregas";
    }

    @PostMapping("/guardar")
    public String save(@RequestParam Long idEnvio, @RequestParam String accion, @RequestParam(required = false) String detalleIncidencia) {
        Optional<Envio> envioOpt = envioService.findById(idEnvio);
        if (envioOpt.isPresent()) {
            Envio envio = envioOpt.get();
            if ("ENTREGADO".equals(accion)) {
                envio.setEstadoPaquete("Entregado");
                envio.getEncomienda().setEstado("Entregado");
            } else if ("INCIDENCIA".equals(accion)) {
                envio.setEstadoPaquete("Pendiente de Entrega");
                envio.getEncomienda().setEstado("Pendiente de Entrega (Incidencia: " + detalleIncidencia + ")");
            }
            envioService.save(envio);
        }
        return "redirect:/entregas";
    }
}

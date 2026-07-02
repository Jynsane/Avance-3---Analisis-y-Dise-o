package com.shalom.tracking.controller;

import com.shalom.tracking.model.Envio;
import com.shalom.tracking.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@RequestMapping("/tracking/public")
public class TrackingController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public String index() {
        return "tracking/search";
    }

    @GetMapping("/buscar")
    public String search(@RequestParam(required = false) String codSeguimiento, Model model) {
        if (codSeguimiento == null || codSeguimiento.isBlank()) {
            model.addAttribute("error", "Por favor ingrese un código de seguimiento.");
            return "tracking/search";
        }

        Optional<Envio> envioOpt = envioService.findByCodSeguimiento(codSeguimiento.trim());
        if (envioOpt.isPresent()) {
            model.addAttribute("envio", envioOpt.get());
            return "tracking/status";
        } else {
            model.addAttribute("error", "No se encontró ningún envío con el código " + codSeguimiento);
            return "tracking/search";
        }
    }
}

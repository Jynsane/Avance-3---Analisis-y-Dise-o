package com.shalom.tracking.controller;

import com.shalom.tracking.model.Comprobante;
import com.shalom.tracking.model.Envio;
import com.shalom.tracking.service.ComprobanteService;
import com.shalom.tracking.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/comprobantes")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("comprobantes", comprobanteService.findAll());
        return "comprobantes/list";
    }

    @GetMapping("/generar/{idEnvio}")
    public String generateForm(@PathVariable Long idEnvio, Model model) {
        Optional<Envio> envioOpt = envioService.findById(idEnvio);
        if (envioOpt.isPresent()) {
            model.addAttribute("envio", envioOpt.get());
            return "comprobantes/generar";
        }
        return "redirect:/envios";
    }

    @PostMapping("/generar")
    public String generate(@RequestParam Long idEnvio, @RequestParam String tipoDocumento) {
        Optional<Envio> envioOpt = envioService.findById(idEnvio);
        if (envioOpt.isPresent()) {
            comprobanteService.generateComprobante(envioOpt.get(), tipoDocumento);
        }
        return "redirect:/comprobantes";
    }

    @GetMapping("/ver/{id}")
    public String view(@PathVariable Long id, Model model) {
        Optional<Comprobante> comprobante = comprobanteService.findById(id);
        if (comprobante.isPresent()) {
            model.addAttribute("comprobante", comprobante.get());
            return "comprobantes/view";
        }
        return "redirect:/comprobantes";
    }

    @GetMapping("/remision/{idEnvio}")
    public String viewRemision(@PathVariable Long idEnvio, Model model) {
        Optional<Envio> envio = envioService.findById(idEnvio);
        if (envio.isPresent()) {
            model.addAttribute("envio", envio.get());
            return "comprobantes/remision";
        }
        return "redirect:/envios";
    }
}

package com.shalom.tracking.controller;

import com.shalom.tracking.model.Cliente;
import com.shalom.tracking.model.Encomienda;
import com.shalom.tracking.model.Envio;
import com.shalom.tracking.model.Usuario;
import com.shalom.tracking.service.ClienteService;
import com.shalom.tracking.service.EnvioService;
import com.shalom.tracking.service.RutaService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RutaService rutaService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("envios", envioService.findAll());
        return "envios/list";
    }

    @GetMapping("/nuevo")
    public String showForm(Model model) {
        Envio envio = new Envio();
        envio.setEncomienda(new Encomienda());
        model.addAttribute("envio", envio);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("rutas", rutaService.findAll());
        return "envios/form";
    }

    @PostMapping("/guardar")
    public String save(@Valid @ModelAttribute("envio") Envio envio, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteService.findAll());
            model.addAttribute("rutas", rutaService.findAll());
            return "envios/form";
        }

        Usuario loggedIn = (Usuario) session.getAttribute("usuarioLogueado");
        envio.setUsuario(loggedIn);
        envio.setFechaRegistro(LocalDate.now());

        // Asegurar que el estado del paquete coincida
        envio.getEncomienda().setEstado("En Almacén");
        envio.setEstadoPaquete("En Almacén");

        // Calcular costo final
        double calculatedCost = envioService.calculateCosto(envio.getEncomienda().getPesoKg(), envio.getEncomienda().getDimensiones());
        envio.setCostoTotal(calculatedCost);

        envioService.save(envio);
        return "redirect:/envios";
    }

    @GetMapping("/ver/{id}")
    public String view(@PathVariable Long id, Model model) {
        Optional<Envio> envio = envioService.findById(id);
        if (envio.isPresent()) {
            model.addAttribute("envio", envio.get());
            return "envios/view";
        }
        return "redirect:/envios";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {
        envioService.deleteById(id);
        return "redirect:/envios";
    }

    @GetMapping("/calcular-costo")
    @ResponseBody
    public Double calculateCost(@RequestParam Double peso, @RequestParam String dimensiones) {
        return envioService.calculateCosto(peso, dimensiones);
    }
}

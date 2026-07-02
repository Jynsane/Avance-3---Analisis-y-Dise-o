package com.shalom.tracking.controller;

import com.shalom.tracking.model.Cliente;
import com.shalom.tracking.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        return "clientes/list";
    }

    @GetMapping("/nuevo")
    public String showForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    @PostMapping("/guardar")
    public String save(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "clientes/form";
        }

        // Si es un nuevo cliente, verificar DNI/RUC único
        if (cliente.getIdCliente() == null) {
            Optional<Cliente> existing = clienteService.findByDniRuc(cliente.getDniRuc());
            if (existing.isPresent()) {
                model.addAttribute("error", "El cliente con DNI/RUC " + cliente.getDniRuc() + " ya está registrado.");
                return "clientes/form";
            }
        }

        clienteService.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "clientes/form";
        }
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {
        clienteService.deleteById(id);
        return "redirect:/clientes";
    }
}

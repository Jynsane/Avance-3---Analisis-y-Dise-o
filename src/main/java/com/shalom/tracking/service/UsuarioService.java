package com.shalom.tracking.service;

import com.shalom.tracking.model.Usuario;
import com.shalom.tracking.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void initDefaultUsers() {
        if (usuarioRepository.count() == 0) {
            usuarioRepository.save(new Usuario("admin", "ADMINISTRADOR", "admin"));
            usuarioRepository.save(new Usuario("recepcionista", "RECEPCIONISTA", "recep"));
            usuarioRepository.save(new Usuario("transportista", "TRANSPORTISTA", "trans"));
            usuarioRepository.save(new Usuario("almacenero", "ALMACENERO", "almacen"));
        }
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario authenticate(String nombre, String contrasena) {
        Optional<Usuario> userOpt = usuarioRepository.findByNombre(nombre);
        if (userOpt.isPresent() && userOpt.get().getContrasena().equals(contrasena)) {
            return userOpt.get();
        }
        return null;
    }
}

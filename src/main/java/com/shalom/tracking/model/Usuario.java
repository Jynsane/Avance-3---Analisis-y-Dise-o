package com.shalom.tracking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Long idUsuario;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 100)
    @Column(name = "Nombre", unique = true, nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El rol es obligatorio")
    @Size(max = 50)
    @Column(name = "Rol", nullable = false, length = 50)
    private String rol; // ADMINISTRADOR, RECEPCIONISTA, TRANSPORTISTA, ALMACENERO

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 255)
    @Column(name = "Contrasena", nullable = false, length = 255)
    private String contrasena;

    public Usuario() {}

    public Usuario(String nombre, String rol, String contrasena) {
        this.nombre = nombre;
        this.rol = rol;
        this.contrasena = contrasena;
    }

    // Getters and Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

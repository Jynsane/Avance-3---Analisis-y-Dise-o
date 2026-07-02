package com.shalom.tracking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "encomiendas")
public class Encomienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cod_Paquete")
    private Long codPaquete;

    @NotBlank(message = "La descripción de la encomienda es obligatoria")
    @Size(max = 200)
    @Column(name = "Descripcion", nullable = false, length = 200)
    private String descripcion;

    @NotNull(message = "El peso es obligatorio")
    @Min(value = 0, message = "El peso no puede ser negativo")
    @Column(name = "Peso_kg", nullable = false)
    private Double pesoKg;

    @NotBlank(message = "Las dimensiones son obligatorias (e.g. 30x30x30)")
    @Size(max = 50)
    @Column(name = "Dimensiones", nullable = false, length = 50)
    private String dimensiones;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 30)
    @Column(name = "Estado", nullable = false, length = 30)
    private String estado = "En Almacén"; // En Almacén, En Tránsito, Entregado, Pendiente de Entrega, Incidencia

    public Encomienda() {}

    public Encomienda(String descripcion, Double pesoKg, String dimensiones, String estado) {
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
        this.estado = estado;
    }

    // Getters and Setters
    public Long getCodPaquete() {
        return codPaquete;
    }

    public void setCodPaquete(Long codPaquete) {
        this.codPaquete = codPaquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

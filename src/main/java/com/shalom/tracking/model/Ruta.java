package com.shalom.tracking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "rutas")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Ruta")
    private Long idRuta;

    @NotBlank(message = "La placa del vehículo es obligatoria")
    @Size(max = 15)
    @Column(name = "Placa_Vehiculo", nullable = false, length = 15)
    private String placaVehiculo;

    @NotBlank(message = "El chofer asignado es obligatorio")
    @Size(max = 100)
    @Column(name = "Chofer_Asignado", nullable = false, length = 100)
    private String choferAsignado;

    @NotBlank(message = "La agencia de destino es obligatoria")
    @Size(max = 100)
    @Column(name = "Agencia_Destino", nullable = false, length = 100)
    private String agenciaDestino;

    public Ruta() {}

    public Ruta(String placaVehiculo, String choferAsignado, String agenciaDestino) {
        this.placaVehiculo = placaVehiculo;
        this.choferAsignado = choferAsignado;
        this.agenciaDestino = agenciaDestino;
    }

    // Getters and Setters
    public Long getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Long idRuta) {
        this.idRuta = idRuta;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getChoferAsignado() {
        return choferAsignado;
    }

    public void setChoferAsignado(String choferAsignado) {
        this.choferAsignado = choferAsignado;
    }

    public String getAgenciaDestino() {
        return agenciaDestino;
    }

    public void setAgenciaDestino(String agenciaDestino) {
        this.agenciaDestino = agenciaDestino;
    }
}

package com.shalom.tracking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "comprobantes")
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Num_Comprobante")
    private Long numComprobante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Cod_Envio", nullable = false)
    private Envio envio;

    @NotBlank(message = "El tipo de documento es obligatorio")
    @Size(max = 30)
    @Column(name = "Tipo_Documento", nullable = false, length = 30)
    private String tipoDocumento; // Factura, Boleta

    @NotNull(message = "El monto total es obligatorio")
    @Min(value = 0, message = "El monto no puede ser negativo")
    @Column(name = "Monto_Total", nullable = false)
    private Double montoTotal;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 150)
    @Column(name = "Nombre_Cliente", nullable = false, length = 150)
    private String nombreCliente;

    @NotBlank(message = "El DNI o RUC es obligatorio")
    @Size(max = 15)
    @Column(name = "DNI_RUC", nullable = false, length = 15)
    private String dniRuc;

    @NotNull(message = "La fecha de emisión es obligatoria")
    @Column(name = "Fecha_Emision", nullable = false)
    private LocalDate fechaEmision;

    public Comprobante() {}

    // Getters and Setters
    public Long getNumComprobante() {
        return numComprobante;
    }

    public void setNumComprobante(Long numComprobante) {
        this.numComprobante = numComprobante;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDniRuc() {
        return dniRuc;
    }

    public void setDniRuc(String dniRuc) {
        this.dniRuc = dniRuc;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
}

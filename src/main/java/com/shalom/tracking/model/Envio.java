package com.shalom.tracking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cod_Envio")
    private Long codEnvio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_Cliente", nullable = false)
    private Cliente cliente; // Remitente/Destinatario principal

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "Cod_Paquete", nullable = false)
    private Encomienda encomienda;

    @ManyToOne
    @JoinColumn(name = "ID_Ruta")
    private Ruta ruta; // Ruta asignada (puede ser null inicialmente)

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false)
    private Usuario usuario; // Recepcionista creador

    @NotBlank(message = "El origen es obligatorio")
    @Size(max = 100)
    @Column(name = "Origen", nullable = false, length = 100)
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    @Size(max = 100)
    @Column(name = "Destino", nullable = false, length = 100)
    private String destino;

    @Column(name = "Costo_Total", nullable = false)
    private Double costoTotal;

    @NotBlank(message = "El tipo de pago es obligatorio")
    @Size(max = 30)
    @Column(name = "Tipo_Pago", nullable = false, length = 30)
    private String tipoPago; // Pago en Origen, Pago en Destino

    @Column(name = "Estado_Paquete", nullable = false, length = 30)
    private String estadoPaquete = "Registrado"; // Registrado, En Almacén, En Tránsito, Entregado, Incidencia

    @Column(name = "Fecha_Registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "Cod_Seguimiento", unique = true, nullable = false, length = 30)
    private String codSeguimiento;

    public Envio() {}

    // Getters and Setters
    public Long getCodEnvio() {
        return codEnvio;
    }

    public void setCodEnvio(Long codEnvio) {
        this.codEnvio = codEnvio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Encomienda getEncomienda() {
        return encomienda;
    }

    public void setEncomienda(Encomienda encomienda) {
        this.encomienda = encomienda;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getEstadoPaquete() {
        return estadoPaquete;
    }

    public void setEstadoPaquete(String estadoPaquete) {
        this.estadoPaquete = estadoPaquete;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCodSeguimiento() {
        return codSeguimiento;
    }

    public void setCodSeguimiento(String codSeguimiento) {
        this.codSeguimiento = codSeguimiento;
    }
}

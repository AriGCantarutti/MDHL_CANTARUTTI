package com.transportista.solicitudes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_solicitud", unique = true, nullable = false)
    private String numeroSolicitud;

    @NotNull(message = "El contenedor es requerido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contenedor_id", nullable = false)
    private Contenedor contenedor;

    @NotNull(message = "El ID del cliente es requerido")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @NotNull(message = "La dirección de origen es requerida")
    @Column(name = "direccion_origen", nullable = false)
    private String direccionOrigen;

    @NotNull(message = "La latitud de origen es requerida")
    @Column(name = "latitud_origen", nullable = false, precision = 10, scale = 7)
    private BigDecimal latitudOrigen;

    @NotNull(message = "La longitud de origen es requerida")
    @Column(name = "longitud_origen", nullable = false, precision = 10, scale = 7)
    private BigDecimal longitudOrigen;

    @NotNull(message = "La dirección de destino es requerida")
    @Column(name = "direccion_destino", nullable = false)
    private String direccionDestino;

    @NotNull(message = "La latitud de destino es requerida")
    @Column(name = "latitud_destino", nullable = false, precision = 10, scale = 7)
    private BigDecimal latitudDestino;

    @NotNull(message = "La longitud de destino es requerida")
    @Column(name = "longitud_destino", nullable = false, precision = 10, scale = 7)
    private BigDecimal longitudDestino;

    @Column(name = "costo_total", precision = 10, scale = 2)
    private BigDecimal costoTotal;

    @Column(name = "tiempo_estimado_horas")
    private Integer tiempoEstimadoHoras;

    @Column(name = "estado", length = 50)
    private String estado; // PENDIENTE, RUTA_ASIGNADA, EN_PROCESO, COMPLETADA, CANCELADA

    @Column(name = "fecha_solicitud")
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_estimada_entrega")
    private LocalDateTime fechaEstimadaEntrega;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        fechaSolicitud = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (estado == null) {
            estado = "PENDIENTE";
        }
        if (numeroSolicitud == null) {
            numeroSolicitud = "SOL-" + System.currentTimeMillis();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}

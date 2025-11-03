package com.transportista.solicitudes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tramos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id", nullable = false)
    private Ruta ruta;

    @Column(name = "numero_orden", nullable = false)
    private Integer numeroOrden; // Para mantener el orden de los tramos

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

    @Column(name = "tipo", length = 50)
    private String tipo; // ORIGEN_DEPOSITO, DEPOSITO_DEPOSITO, DEPOSITO_DESTINO, ORIGEN_DESTINO

    @Column(name = "distancia_km", precision = 10, scale = 2)
    private BigDecimal distanciaKm;

    @Column(name = "costo", precision = 10, scale = 2)
    private BigDecimal costo;

    @Column(name = "estado", length = 50)
    private String estado; // PENDIENTE, ASIGNADO, EN_CURSO, COMPLETADO

    @Column(name = "camion_id")
    private Long camionId;

    @Column(name = "transportista")
    private String transportista;

    @Column(name = "deposito_origen_id")
    private Long depositoOrigenId;

    @Column(name = "deposito_destino_id")
    private Long depositoDestinoId;

    @Column(name = "fecha_inicio_real")
    private LocalDateTime fechaInicioReal;

    @Column(name = "fecha_fin_real")
    private LocalDateTime fechaFinReal;

    @Column(name = "fecha_estimada_inicio")
    private LocalDateTime fechaEstimadaInicio;

    @Column(name = "fecha_estimada_fin")
    private LocalDateTime fechaEstimadaFin;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (estado == null) {
            estado = "PENDIENTE";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}

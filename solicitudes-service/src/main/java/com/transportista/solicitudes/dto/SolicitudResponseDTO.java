package com.transportista.solicitudes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudResponseDTO {
    
    private Long id;
    private String numeroSolicitud;
    private Long contenedorId;
    private Long clienteId;
    private String direccionOrigen;
    private String direccionDestino;
    private BigDecimal costoTotal;
    private Integer tiempoEstimadoHoras;
    private String estado;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaEstimadaEntrega;
}

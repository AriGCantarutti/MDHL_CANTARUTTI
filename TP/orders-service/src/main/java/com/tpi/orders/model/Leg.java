package com.tpi.orders.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "legs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Leg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double originLat;
    private Double originLng;
    private Double destLat;
    private Double destLng;

    private String type; // origen-deposito, deposito-deposito, deposito-destino, origen-destino

    private String status; // estimado, asignado, iniciado, finalizado

    private Double distanceKm;
    private Double costEstimated;
    private Double costReal;

    private LocalDateTime estimatedStart;
    private LocalDateTime estimatedEnd;
    private LocalDateTime realStart;
    private LocalDateTime realEnd;

    private Long truckId; // referencia al camion asignado (id en fleet-service)

    @ManyToOne
    private RequestEntity request;
}


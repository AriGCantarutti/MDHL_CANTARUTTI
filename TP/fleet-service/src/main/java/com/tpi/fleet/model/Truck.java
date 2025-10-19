package com.tpi.fleet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trucks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String licensePlate; // dominio/patente

    private String driverName; // nombreTransportista
    private String driverPhone; // teléfono

    private Double maxWeightKg; // capacidad peso
    private Double maxVolumeM3; // capacidad volumen
    private Double fuelConsumptionLPerKm; // consumo combustible por km
    private Double costPerKm; // costo por kilómetro

    private Boolean available; // disponibilidad

    private String status; // libre, ocupado, mantenimiento

    // Para tracking de tramos actuales
    private Long currentLegId;
}

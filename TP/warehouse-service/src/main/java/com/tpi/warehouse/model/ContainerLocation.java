package com.tpi.warehouse.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "container_locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long containerId; // referencia al contenedor en orders-service
    private String containerIdentifier;

    @ManyToOne
    private Warehouse warehouse;

    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private String status; // en_deposito, retirado
    private Long legId; // referencia al tramo que lo depositó
}

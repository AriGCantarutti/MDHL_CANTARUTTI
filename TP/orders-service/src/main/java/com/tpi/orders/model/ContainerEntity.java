package com.tpi.orders.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "containers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier; // identificación única proporcionada
    private Double weightKg;
    private Double volumeM3;
    private String status; // borrador, programada, en_transito, entregada

    @ManyToOne
    private Client client;
}


package com.tpi.orders.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Duration;

@Entity
@Table(name = "requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @OneToOne(cascade = CascadeType.ALL)
    private ContainerEntity container;

    @ManyToOne
    private Client client;

    private Double costEstimated;
    private Double timeEstimatedHours;

    private Double costFinal;
    private Double timeRealHours;

    private String status; // borrador, programada, en_transito, entregada
}


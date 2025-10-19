package com.tpi.warehouse.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "warehouses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double capacity; // capacidad máxima de contenedores
    private Double currentOccupancy; // ocupación actual
    private Boolean active;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<ContainerLocation> containers;
}

package com.tpi.warehouse.repository;

import com.tpi.warehouse.model.ContainerLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ContainerLocationRepository extends JpaRepository<ContainerLocation, Long> {
    List<ContainerLocation> findByWarehouseIdAndStatus(Long warehouseId, String status);
    List<ContainerLocation> findByStatus(String status);
    Optional<ContainerLocation> findByContainerIdAndStatus(Long containerId, String status);
    List<ContainerLocation> findByContainerIdentifier(String containerIdentifier);
}

package com.tpi.warehouse.service;

import com.tpi.warehouse.model.ContainerLocation;
import com.tpi.warehouse.model.Warehouse;
import com.tpi.warehouse.repository.ContainerLocationRepository;
import com.tpi.warehouse.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final ContainerLocationRepository containerLocationRepository;

    public WarehouseService(WarehouseRepository warehouseRepository,
                           ContainerLocationRepository containerLocationRepository) {
        this.warehouseRepository = warehouseRepository;
        this.containerLocationRepository = containerLocationRepository;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public List<Warehouse> getAvailableWarehouses() {
        return warehouseRepository.findAvailableWarehouses();
    }

    public List<Warehouse> findWarehousesNear(Double lat, Double lng, Double radiusKm) {
        return warehouseRepository.findWarehousesWithinRadius(lat, lng, radiusKm);
    }

    public ContainerLocation checkInContainer(Long containerId, String containerIdentifier,
                                            Long warehouseId, Long legId) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseId);
        if (warehouse.isEmpty()) {
            throw new RuntimeException("Warehouse not found");
        }

        Warehouse w = warehouse.get();
        if (w.getCurrentOccupancy() >= w.getCapacity()) {
            throw new RuntimeException("Warehouse at capacity");
        }

        ContainerLocation location = ContainerLocation.builder()
                .containerId(containerId)
                .containerIdentifier(containerIdentifier)
                .warehouse(w)
                .arrivalTime(LocalDateTime.now())
                .status("en_deposito")
                .legId(legId)
                .build();

        // Update warehouse occupancy
        w.setCurrentOccupancy(w.getCurrentOccupancy() + 1);
        warehouseRepository.save(w);

        return containerLocationRepository.save(location);
    }

    public ContainerLocation checkOutContainer(Long containerId) {
        Optional<ContainerLocation> location = containerLocationRepository
                .findByContainerIdAndStatus(containerId, "en_deposito");

        if (location.isEmpty()) {
            throw new RuntimeException("Container not found in warehouse");
        }

        ContainerLocation loc = location.get();
        loc.setDepartureTime(LocalDateTime.now());
        loc.setStatus("retirado");

        // Update warehouse occupancy
        Warehouse w = loc.getWarehouse();
        w.setCurrentOccupancy(Math.max(0, w.getCurrentOccupancy() - 1));
        warehouseRepository.save(w);

        return containerLocationRepository.save(loc);
    }

    public List<ContainerLocation> getContainersInWarehouse(Long warehouseId) {
        return containerLocationRepository.findByWarehouseIdAndStatus(warehouseId, "en_deposito");
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        if (warehouse.getCurrentOccupancy() == null) {
            warehouse.setCurrentOccupancy(0.0);
        }
        return warehouseRepository.save(warehouse);
    }

    public Optional<ContainerLocation> findContainerLocation(Long containerId) {
        return containerLocationRepository.findByContainerIdAndStatus(containerId, "en_deposito");
    }
}

package com.tpi.warehouse.controller;

import com.tpi.warehouse.model.ContainerLocation;
import com.tpi.warehouse.model.Warehouse;
import com.tpi.warehouse.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return ResponseEntity.ok(warehouseService.getAllWarehouses());
    }

    @GetMapping("/available")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Warehouse>> getAvailableWarehouses() {
        return ResponseEntity.ok(warehouseService.getAvailableWarehouses());
    }

    @GetMapping("/near")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Warehouse>> findWarehousesNear(
            @RequestParam Double lat,
            @RequestParam Double lng,
            @RequestParam(defaultValue = "50") Double radiusKm) {
        return ResponseEntity.ok(warehouseService.findWarehousesNear(lat, lng, radiusKm));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Warehouse> createWarehouse(@Valid @RequestBody Warehouse warehouse) {
        warehouse.setActive(true);
        return ResponseEntity.ok(warehouseService.saveWarehouse(warehouse));
    }

    @PostMapping("/{warehouseId}/checkin")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN') or hasRole('DRIVER')")
    public ResponseEntity<ContainerLocation> checkInContainer(
            @PathVariable Long warehouseId,
            @RequestParam Long containerId,
            @RequestParam String containerIdentifier,
            @RequestParam Long legId) {
        try {
            ContainerLocation location = warehouseService.checkInContainer(
                containerId, containerIdentifier, warehouseId, legId);
            return ResponseEntity.ok(location);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/checkout/{containerId}")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN') or hasRole('DRIVER')")
    public ResponseEntity<ContainerLocation> checkOutContainer(@PathVariable Long containerId) {
        try {
            ContainerLocation location = warehouseService.checkOutContainer(containerId);
            return ResponseEntity.ok(location);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{warehouseId}/containers")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<ContainerLocation>> getContainersInWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(warehouseService.getContainersInWarehouse(warehouseId));
    }

    @GetMapping("/containers/{containerId}/location")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN') or hasRole('CLIENT')")
    public ResponseEntity<ContainerLocation> findContainerLocation(@PathVariable Long containerId) {
        return warehouseService.findContainerLocation(containerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

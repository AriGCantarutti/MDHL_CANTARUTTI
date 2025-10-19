package com.tpi.fleet.controller;

import com.tpi.fleet.model.Truck;
import com.tpi.fleet.service.TruckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trucks")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Truck>> getAllTrucks() {
        return ResponseEntity.ok(truckService.getAllTrucks());
    }

    @GetMapping("/available")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Truck>> getAvailableTrucks() {
        return ResponseEntity.ok(truckService.findAvailableTrucks());
    }

    @GetMapping("/available-for-container")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Truck>> getAvailableTrucksForContainer(
            @RequestParam Double weightKg,
            @RequestParam Double volumeM3) {
        return ResponseEntity.ok(truckService.findAvailableTrucksForContainer(weightKg, volumeM3));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Truck> createTruck(@Valid @RequestBody Truck truck) {
        truck.setAvailable(true);
        truck.setStatus("libre");
        return ResponseEntity.ok(truckService.saveTruck(truck));
    }

    @PostMapping("/{truckId}/assign/{legId}")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Truck> assignTruckToLeg(@PathVariable Long truckId, @PathVariable Long legId) {
        try {
            Truck truck = truckService.assignTruckToLeg(truckId, legId);
            return ResponseEntity.ok(truck);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{truckId}/release")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN') or hasRole('DRIVER')")
    public ResponseEntity<Truck> releaseTruck(@PathVariable Long truckId) {
        try {
            Truck truck = truckService.releaseTruck(truckId);
            return ResponseEntity.ok(truck);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN') or hasRole('DRIVER')")
    public ResponseEntity<Truck> getTruckById(@PathVariable Long id) {
        return truckService.getTruckById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

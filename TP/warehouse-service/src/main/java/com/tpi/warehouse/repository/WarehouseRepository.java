package com.tpi.warehouse.repository;

import com.tpi.warehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findByActiveTrue();

    @Query("SELECT w FROM Warehouse w WHERE w.capacity - w.currentOccupancy >= 1 AND w.active = true")
    List<Warehouse> findAvailableWarehouses();

    @Query("SELECT w FROM Warehouse w WHERE " +
           "6371 * acos(cos(radians(?1)) * cos(radians(w.latitude)) * " +
           "cos(radians(w.longitude) - radians(?2)) + sin(radians(?1)) * " +
           "sin(radians(w.latitude))) <= ?3 AND w.active = true")
    List<Warehouse> findWarehousesWithinRadius(Double lat, Double lng, Double radiusKm);
}

package com.tpi.fleet.repository;

import com.tpi.fleet.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TruckRepository extends JpaRepository<Truck, Long> {
    List<Truck> findByStatus(String status);
    List<Truck> findByAvailableTrue();

    @Query("SELECT t FROM Truck t WHERE t.maxWeightKg >= ?1 AND t.maxVolumeM3 >= ?2 AND t.available = true")
    List<Truck> findAvailableTrucksForContainer(Double weightKg, Double volumeM3);
}

package com.tpi.fleet.service;

import com.tpi.fleet.model.Truck;
import com.tpi.fleet.repository.TruckRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TruckService {

    private final TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public List<Truck> findAvailableTrucksForContainer(Double weightKg, Double volumeM3) {
        return truckRepository.findAvailableTrucksForContainer(weightKg, volumeM3);
    }

    public List<Truck> findAvailableTrucks() {
        return truckRepository.findByAvailableTrue();
    }

    public List<Truck> findTrucksByStatus(String status) {
        return truckRepository.findByStatus(status);
    }

    public Truck assignTruckToLeg(Long truckId, Long legId) {
        Optional<Truck> truck = truckRepository.findById(truckId);
        if (truck.isPresent() && truck.get().getAvailable()) {
            Truck t = truck.get();
            t.setCurrentLegId(legId);
            t.setAvailable(false);
            t.setStatus("ocupado");
            return truckRepository.save(t);
        }
        throw new RuntimeException("Truck not available or not found");
    }

    public Truck releaseTruck(Long truckId) {
        Optional<Truck> truck = truckRepository.findById(truckId);
        if (truck.isPresent()) {
            Truck t = truck.get();
            t.setCurrentLegId(null);
            t.setAvailable(true);
            t.setStatus("libre");
            return truckRepository.save(t);
        }
        throw new RuntimeException("Truck not found");
    }

    public Truck saveTruck(Truck truck) {
        return truckRepository.save(truck);
    }

    public List<Truck> getAllTrucks() {
        return truckRepository.findAll();
    }

    public Optional<Truck> getTruckById(Long id) {
        return truckRepository.findById(id);
    }
}

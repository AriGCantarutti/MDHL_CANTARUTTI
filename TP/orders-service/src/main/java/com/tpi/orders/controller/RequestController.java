package com.tpi.orders.controller;

import com.tpi.orders.model.*;
import com.tpi.orders.repository.*;
import com.tpi.orders.service.EstimationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestRepository requestRepository;
    private final ClientRepository clientRepository;
    private final ContainerRepository containerRepository;
    private final LegRepository legRepository;
    private final EstimationService estimationService;

    public RequestController(RequestRepository requestRepository, ClientRepository clientRepository, ContainerRepository containerRepository, LegRepository legRepository, EstimationService estimationService) {
        this.requestRepository = requestRepository;
        this.clientRepository = clientRepository;
        this.containerRepository = containerRepository;
        this.legRepository = legRepository;
        this.estimationService = estimationService;
    }

    @PostMapping
    public ResponseEntity<RequestEntity> createRequest(@Valid @RequestBody RequestEntity req) {
        // ensure client exists or create
        if (req.getClient() != null && req.getClient().getEmail() != null) {
            Optional<Client> existing = clientRepository.findByEmail(req.getClient().getEmail());
            existing.ifPresent(client -> req.setClient(client));
        }
        if (req.getContainer() != null) {
            req.getContainer().setStatus("borrador");
        }
        req.setStatus("borrador");
        RequestEntity saved = requestRepository.save(req);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRequest(@PathVariable Long id) {
        return requestRepository.findById(id)
                .map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/estimate")
    public ResponseEntity<?> estimate(@PathVariable Long id, @RequestBody List<Leg> legs) {
        Optional<RequestEntity> or = requestRepository.findById(id);
        if (or.isEmpty()) return ResponseEntity.notFound().build();
        // estimate legs
        for (Leg l : legs) {
            estimationService.estimateLeg(l);
            l.setRequest(or.get());
            legRepository.save(l);
        }
        double totalCost = estimationService.estimateTotalCost(legs);
        double totalDist = estimationService.estimateTotalDistance(legs);
        RequestEntity req = or.get();
        req.setCostEstimated(totalCost);
        req.setTimeEstimatedHours(totalDist / 60.0); // assume 60 km/h average
        requestRepository.save(req);
        return ResponseEntity.ok(new EstimationResponse(totalCost, totalDist));
    }

    record EstimationResponse(double costEstimated, double distanceKm) {}
}

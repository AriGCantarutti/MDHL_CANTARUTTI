package com.tpi.pricing.controller;

import com.tpi.pricing.service.PricingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pricing")
@Tag(name = "Pricing", description = "API para cálculo de precios y rutas")
@SecurityRequirement(name = "bearerAuth")
public class PricingController {

    @Autowired
    private PricingService pricingService;

    @PostMapping("/calculate")
    @Operation(summary = "Calcular costo de transporte")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    public ResponseEntity<Map<String, Object>> calculateShippingCost(@RequestBody Map<String, Object> request) {
        try {
            double originLat = ((Number) request.get("originLat")).doubleValue();
            double originLng = ((Number) request.get("originLng")).doubleValue();
            double destLat = ((Number) request.get("destLat")).doubleValue();
            double destLng = ((Number) request.get("destLng")).doubleValue();
            BigDecimal containerWeight = new BigDecimal(request.get("containerWeight").toString());
            BigDecimal containerVolume = new BigDecimal(request.get("containerVolume").toString());
            BigDecimal truckConsumption = new BigDecimal(request.get("truckConsumption").toString());

            @SuppressWarnings("unchecked")
            List<Map<String, Double>> warehouses = (List<Map<String, Double>>) request.get("warehouses");

            BigDecimal cost = pricingService.calculateRouteCost(
                originLat, originLng, destLat, destLng,
                containerWeight, containerVolume, truckConsumption, warehouses);

            BigDecimal estimatedTime = pricingService.calculateEstimatedTime(
                originLat, originLng, destLat, destLng, warehouses);

            return ResponseEntity.ok(Map.of(
                "estimatedCost", cost,
                "estimatedTimeHours", estimatedTime,
                "success", true
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Error calculating pricing: " + e.getMessage(),
                "success", false
            ));
        }
    }

    @GetMapping("/health")
    @Operation(summary = "Verificar estado del servicio")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}

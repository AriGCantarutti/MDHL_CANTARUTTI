package com.tpi.pricing.service;

import com.tpi.pricing.model.PricingRule;
import com.tpi.pricing.repository.PricingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
public class PricingService {

    @Autowired
    private PricingRuleRepository pricingRuleRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.google-maps-key}")
    private String googleMapsApiKey;

    @Value("${app.fuel-price-per-litre}")
    private BigDecimal fuelPricePerLitre;

    public BigDecimal calculateRouteCost(double originLat, double originLng,
                                       double destLat, double destLng,
                                       BigDecimal containerWeight, BigDecimal containerVolume,
                                       BigDecimal truckConsumption, List<Map<String, Double>> warehouses) {

        PricingRule rule = getActivePricingRule();
        BigDecimal totalCost = BigDecimal.ZERO;

        // Calcular distancia total usando Google Maps
        BigDecimal totalDistance = calculateTotalDistance(originLat, originLng, destLat, destLng, warehouses);

        // Costo base por kilómetro
        BigDecimal baseCost = totalDistance.multiply(rule.getBaseCostPerKm());

        // Factor por peso y volumen
        BigDecimal weightCost = containerWeight.multiply(rule.getWeightFactor());
        BigDecimal volumeCost = containerVolume.multiply(rule.getVolumeFactor());

        // Costo de combustible
        BigDecimal fuelCost = totalDistance.multiply(truckConsumption).multiply(fuelPricePerLitre);

        // Costo de gestión por tramo
        int numberOfLegs = warehouses.size() + 1; // +1 para el tramo final
        BigDecimal managementCost = rule.getManagementFeePerLeg().multiply(BigDecimal.valueOf(numberOfLegs));

        totalCost = baseCost.add(weightCost).add(volumeCost).add(fuelCost).add(managementCost);

        return totalCost.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateEstimatedTime(double originLat, double originLng,
                                           double destLat, double destLng,
                                           List<Map<String, Double>> warehouses) {
        BigDecimal totalDistance = calculateTotalDistance(originLat, originLng, destLat, destLng, warehouses);
        // Asumiendo velocidad promedio de 60 km/h
        return totalDistance.divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTotalDistance(double originLat, double originLng,
                                            double destLat, double destLng,
                                            List<Map<String, Double>> warehouses) {
        BigDecimal totalDistance = BigDecimal.ZERO;

        if (warehouses.isEmpty()) {
            // Ruta directa
            return getDistanceFromGoogleMaps(originLat, originLng, destLat, destLng);
        }

        // Distancia desde origen al primer depósito
        Map<String, Double> firstWarehouse = warehouses.get(0);
        totalDistance = totalDistance.add(getDistanceFromGoogleMaps(
            originLat, originLng,
            firstWarehouse.get("latitude"), firstWarehouse.get("longitude")));

        // Distancias entre depósitos
        for (int i = 0; i < warehouses.size() - 1; i++) {
            Map<String, Double> current = warehouses.get(i);
            Map<String, Double> next = warehouses.get(i + 1);
            totalDistance = totalDistance.add(getDistanceFromGoogleMaps(
                current.get("latitude"), current.get("longitude"),
                next.get("latitude"), next.get("longitude")));
        }

        // Distancia desde último depósito al destino
        Map<String, Double> lastWarehouse = warehouses.get(warehouses.size() - 1);
        totalDistance = totalDistance.add(getDistanceFromGoogleMaps(
            lastWarehouse.get("latitude"), lastWarehouse.get("longitude"),
            destLat, destLng));

        return totalDistance;
    }

    private BigDecimal getDistanceFromGoogleMaps(double originLat, double originLng,
                                               double destLat, double destLng) {
        if (googleMapsApiKey == null || googleMapsApiKey.isEmpty()) {
            // Fallback: calcular distancia euclidiana aproximada
            return calculateEuclideanDistance(originLat, originLng, destLat, destLng);
        }

        try {
            String url = String.format(
                "https://maps.googleapis.com/maps/api/directions/json?origin=%f,%f&destination=%f,%f&key=%s",
                originLat, originLng, destLat, destLng, googleMapsApiKey);

            @SuppressWarnings("unchecked")
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response != null && "OK".equals(response.get("status"))) {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> routes = (List<Map<String, Object>>) response.get("routes");
                if (!routes.isEmpty()) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> route = routes.get(0);
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> legs = (List<Map<String, Object>>) route.get("legs");
                    if (!legs.isEmpty()) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> distance = (Map<String, Object>) legs.get(0).get("distance");
                        Integer distanceValue = (Integer) distance.get("value");
                        return BigDecimal.valueOf(distanceValue / 1000.0); // Convertir metros a kilómetros
                    }
                }
            }
        } catch (Exception e) {
            // En caso de error, usar distancia euclidiana
        }

        return calculateEuclideanDistance(originLat, originLng, destLat, destLng);
    }

    private BigDecimal calculateEuclideanDistance(double lat1, double lng1, double lat2, double lng2) {
        double R = 6371; // Radio de la Tierra en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                  Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                  Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return BigDecimal.valueOf(R * c);
    }

    private PricingRule getActivePricingRule() {
        List<PricingRule> rules = pricingRuleRepository.findAll();
        if (rules.isEmpty()) {
            // Crear regla por defecto si no existe
            PricingRule defaultRule = new PricingRule(
                BigDecimal.valueOf(2.0),    // Base cost per km
                BigDecimal.valueOf(0.1),    // Weight factor
                BigDecimal.valueOf(0.05),   // Volume factor
                BigDecimal.valueOf(50.0),   // Warehouse cost per day
                BigDecimal.valueOf(100.0)   // Management fee per leg
            );
            return pricingRuleRepository.save(defaultRule);
        }
        return rules.get(0);
    }
}

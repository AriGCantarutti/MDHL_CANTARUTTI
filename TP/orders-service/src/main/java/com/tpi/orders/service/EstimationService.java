package com.tpi.orders.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tpi.orders.model.Leg;

import java.util.List;

@Service
public class EstimationService {

    @Value("${app.management-fee-per-leg:50.0}")
    private double managementFeePerLeg;

    @Value("${app.fuel-price-per-litre:1.5}")
    private double fuelPricePerLitre;

    // consumo promedio asumido litros/km
    private static final double DEFAULT_CONSUMPTION_L_PER_KM = 0.3;

    public double haversineDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return distance;
    }

    public void estimateLeg(Leg leg) {
        double d = haversineDistanceKm(leg.getOriginLat(), leg.getOriginLng(), leg.getDestLat(), leg.getDestLng());
        leg.setDistanceKm(d);
        double costPerKm = 10.0; // placeholder, can be adjusted with fleet data
        double management = managementFeePerLeg;
        double fuelCost = d * DEFAULT_CONSUMPTION_L_PER_KM * fuelPricePerLitre;
        leg.setCostEstimated(d * costPerKm + management + fuelCost);
    }

    public double estimateTotalCost(List<Leg> legs) {
        double total = 0;
        for (Leg l : legs) {
            if (l.getDistanceKm() == null || l.getDistanceKm() == 0) {
                estimateLeg(l);
            }
            total += l.getCostEstimated();
        }
        return total;
    }

    public double estimateTotalDistance(List<Leg> legs) {
        double total = 0;
        for (Leg l : legs) {
            if (l.getDistanceKm() == null) estimateLeg(l);
            total += l.getDistanceKm();
        }
        return total;
    }
}

package com.tpi.pricing.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pricing_rules")
public class PricingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "base_cost_per_km")
    private BigDecimal baseCostPerKm;

    @NotNull
    @Column(name = "weight_factor")
    private BigDecimal weightFactor;

    @NotNull
    @Column(name = "volume_factor")
    private BigDecimal volumeFactor;

    @NotNull
    @Column(name = "warehouse_cost_per_day")
    private BigDecimal warehouseCostPerDay;

    @NotNull
    @Column(name = "management_fee_per_leg")
    private BigDecimal managementFeePerLeg;

    // Constructors
    public PricingRule() {}

    public PricingRule(BigDecimal baseCostPerKm, BigDecimal weightFactor,
                      BigDecimal volumeFactor, BigDecimal warehouseCostPerDay,
                      BigDecimal managementFeePerLeg) {
        this.baseCostPerKm = baseCostPerKm;
        this.weightFactor = weightFactor;
        this.volumeFactor = volumeFactor;
        this.warehouseCostPerDay = warehouseCostPerDay;
        this.managementFeePerLeg = managementFeePerLeg;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getBaseCostPerKm() { return baseCostPerKm; }
    public void setBaseCostPerKm(BigDecimal baseCostPerKm) { this.baseCostPerKm = baseCostPerKm; }

    public BigDecimal getWeightFactor() { return weightFactor; }
    public void setWeightFactor(BigDecimal weightFactor) { this.weightFactor = weightFactor; }

    public BigDecimal getVolumeFactor() { return volumeFactor; }
    public void setVolumeFactor(BigDecimal volumeFactor) { this.volumeFactor = volumeFactor; }

    public BigDecimal getWarehouseCostPerDay() { return warehouseCostPerDay; }
    public void setWarehouseCostPerDay(BigDecimal warehouseCostPerDay) { this.warehouseCostPerDay = warehouseCostPerDay; }

    public BigDecimal getManagementFeePerLeg() { return managementFeePerLeg; }
    public void setManagementFeePerLeg(BigDecimal managementFeePerLeg) { this.managementFeePerLeg = managementFeePerLeg; }
}

package com.gevernova.carbonemissioncalculator;

// Concrete power unit implementations
public class SolarUnit extends PowerUnit {
    private double panelEfficiency; // in percentage

    public SolarUnit(String name, double capacity, double panelEfficiency) {
        super(name, capacity, FuelType.SOLAR);
        this.panelEfficiency = panelEfficiency;
    }

    @Override
    public String getUnitDetails() {
        return String.format("Solar Unit (Efficiency: %.1f%%)", panelEfficiency);
    }
}
package com.gevernova.carbonemissioncalculator;

public class WindUnit extends PowerUnit {
    private double turbineDiameter; // in meters

    public WindUnit(String name, double capacity, double turbineDiameter) {
        super(name, capacity, FuelType.WIND);
        this.turbineDiameter = turbineDiameter;
    }

    @Override
    public String getUnitDetails() {
        return String.format("Wind Unit (Turbine Diameter: %.1f m)", turbineDiameter);
    }
}
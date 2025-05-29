package com.gevernova.carbonemissioncalculator;

public class GasUnit extends PowerUnit {
    private double combustionEfficiency; // in percentage

    public GasUnit(String name, double capacity, double combustionEfficiency) {
        super(name, capacity, FuelType.NATURAL_GAS);
        this.combustionEfficiency = combustionEfficiency;
    }

    @Override
    public String getUnitDetails() {
        return String.format("Gas Unit (Combustion Efficiency: %.1f%%)", combustionEfficiency);
    }
}
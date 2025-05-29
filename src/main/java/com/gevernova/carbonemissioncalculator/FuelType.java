package com.gevernova.carbonemissioncalculator;

// Enum for different fuel types with their emission factors (kg CO2 per kWh)
public enum FuelType {
    NATURAL_GAS(0.5),
    DIESEL(0.8),
    BIOGAS(0.2),
    SOLAR(0.0),
    WIND(0.0),
    HYDRO(0.0);

    private final double emissionFactor;

    FuelType(double emissionFactor) {
        this.emissionFactor = emissionFactor;
    }

    public double getEmissionFactor() {
        return emissionFactor;
    }
}


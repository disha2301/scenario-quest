package com.gevernova.carbonemissioncalculator;

abstract class PowerUnit {
    private String name;
    private double capacity; // in kW
    private double production; // in kWh
    private FuelType fuelType;

    public PowerUnit(String name, double capacity, FuelType fuelType) {
        this.name = name;
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.production = 0;
    }

    // Getters
    public String getName() { return name; }
    public double getCapacity() { return capacity; }
    public double getProduction() { return production; }
    public FuelType getFuelType() { return fuelType; }

    // Set production with validation
    public void setProduction(double production) throws NegativeEmissionException {
        if (production < 0) {
            throw new NegativeEmissionException("Production cannot be negative for " + name);
        }
        this.production = production;
    }

    // Calculate emissions for this unit
    public double calculateEmissions() {
        return production * fuelType.getEmissionFactor();
    }

    // Abstract method for unit-specific details
    public abstract String getUnitDetails();

    @Override
    public String toString() {
        return String.format("%s [%s, Capacity: %.1f kW, Production: %.1f kWh, Emissions: %.1f kg CO2]",
                name, fuelType, capacity, production, calculateEmissions());
    }
}


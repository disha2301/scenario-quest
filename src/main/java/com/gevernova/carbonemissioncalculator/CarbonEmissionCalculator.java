package com.gevernova.carbonemissioncalculator;

import java.io.IOException;

public class CarbonEmissionCalculator {
    public static void main(String[] args) {
        // Create a hybrid plant
        HybridPlant plant = new HybridPlant("Green Valley Hybrid Plant");

        try {
            // Add different power units
            plant.addPowerUnit(new SolarUnit("Solar Farm 1", 5000, 18.5));
            plant.addPowerUnit(new SolarUnit("Solar Farm 2", 3000, 19.2));
            plant.addPowerUnit(new GasUnit("Gas Turbine 1", 10000, 45.0));
            plant.addPowerUnit(new GasUnit("Gas Turbine 2", 8000, 42.5));
            plant.addPowerUnit(new WindUnit("Wind Farm A", 4000, 120.0));

            // Set production values
            plant.getUnitsOfType(SolarUnit.class).forEach(unit -> {
                try {
                    unit.setProduction(unit.getCapacity() * 0.8 * 24); // 80% capacity for 24 hours
                } catch (NegativeEmissionException e) {
                    System.err.println(e.getMessage());
                }
            });

            plant.getUnitsOfType(GasUnit.class).forEach(unit -> {
                try {
                    unit.setProduction(unit.getCapacity() * 0.6 * 24); // 60% capacity for 24 hours
                } catch (NegativeEmissionException e) {
                    System.err.println(e.getMessage());
                }
            });

            plant.getUnitsOfType(WindUnit.class).forEach(unit -> {
                try {
                    unit.setProduction(unit.getCapacity() * 0.7 * 24); // 70% capacity for 24 hours
                } catch (NegativeEmissionException e) {
                    System.err.println(e.getMessage());
                }
            });

            // Generate and print report
            System.out.println(plant.generateEmissionReport());

            // Save report to file
            plant.saveEmissionReport("emission_report.txt");

            // Test edge cases
            System.out.println("\nTesting edge cases...");
            try {
                PowerUnit testUnit = new GasUnit("Test Unit", 1000, 40.0);
                testUnit.setProduction(-100); // Should throw exception
            } catch (NegativeEmissionException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }

            // Test empty plant
            HybridPlant emptyPlant = new HybridPlant("Empty Plant");
            System.out.println("\nEmpty plant total emissions: " + emptyPlant.calculateTotalEmissions());

        } catch (NegativeEmissionException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
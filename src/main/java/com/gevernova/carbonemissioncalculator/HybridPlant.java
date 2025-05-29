package com.gevernova.carbonemissioncalculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

// Hybrid plant that contains multiple power units
public class HybridPlant {
    private String name;
    private List<PowerUnit> powerUnits;

    public HybridPlant(String name) {
        this.name = name;
        this.powerUnits = new ArrayList<>();
    }

    public void addPowerUnit(PowerUnit unit) {
        powerUnits.add(unit);
    }

    public String getName() {
        return name;
    }

    // Calculate total emissions using Java 8 Streams
    public double calculateTotalEmissions() throws NegativeEmissionException {
        return powerUnits.stream()
                .mapToDouble(PowerUnit::calculateEmissions)
                .sum();
    }

    public Map<FuelType, Double> calculateEmissionsByType() throws NegativeEmissionException {
        return powerUnits.stream()
                .collect(Collectors.groupingBy(
                        PowerUnit::getFuelType,
                        Collectors.summingDouble(PowerUnit::calculateEmissions)
                ));
    }

    // Generate emission report
    public String generateEmissionReport() throws NegativeEmissionException {
        StringBuilder report = new StringBuilder();
        report.append(String.format("Emission Report for %s%n", name));
        char[] chars = new char[50];
        Arrays.fill(chars, '=');
        report.append(new String(chars)).append("\n");


        // Individual units
        report.append("Individual Units:\n");
        powerUnits.forEach(unit -> report.append(unit).append("\n"));

        // Total emissions
        report.append(String.format("%nTotal Emissions: %.1f kg CO2%n", calculateTotalEmissions()));

        // Emissions by type
        report.append("\nEmissions by Fuel Type:\n");
        calculateEmissionsByType().forEach((type, emissions) ->
                report.append(String.format("- %s: %.1f kg CO2%n", type, emissions)));

        return report.toString();
    }

    // Save report to file
    public void saveEmissionReport(String filename) throws IOException, NegativeEmissionException {
        String report = generateEmissionReport();
        Files.write(Paths.get(filename), report.getBytes());
        System.out.println("Report saved to " + filename);
    }

    // Get all units of a specific type (demonstrating Liskov Substitution)
    public <T extends PowerUnit> List<T> getUnitsOfType(Class<T> unitType) {
        return powerUnits.stream()
                .filter(unitType::isInstance)
                .map(unitType::cast)
                .collect(Collectors.toList());
    }
}

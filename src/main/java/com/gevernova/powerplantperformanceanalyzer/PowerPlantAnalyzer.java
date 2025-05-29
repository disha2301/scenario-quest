package com.gevernova.powerplantperformanceanalyzer;

import com.gevernova.powerplantperformanceanalyzer.strategies.PerformanceCalculationStrategy;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class PowerPlantAnalyzer {
    private Map<String, List<Reading>> plantReadings = new HashMap<>();
    private PerformanceCalculationStrategy strategy;

    public void addReading(String plantId, Reading reading) {
        plantReadings.computeIfAbsent(plantId, k -> new ArrayList<>()).add(reading);
    }

    public PowerPlantAnalyzer(PerformanceCalculationStrategy strategy){
        this.strategy = strategy;
    }
    public double calculateEfficiency(String plantId) throws MissingReadingException, DataCorruptionException {
        List<Reading> readings = plantReadings.get(plantId);
        double efficiency = strategy.calculateEfficiency(readings);
        return efficiency;
    }
    public double calculateUptime(String plantId, Instant startTime, Instant endTime) {
        List<Reading> readings = plantReadings.getOrDefault(plantId, Collections.emptyList());
        double uptime = strategy.calculateUpTime(readings, startTime, endTime);
        return uptime;
    }
    public double calculateOutputDeviation(String plantId) {
        List<Reading> readings = plantReadings.getOrDefault(plantId, Collections.emptyList());
        double deviation = strategy.calculateOutputDeviation(readings);
        return deviation;
    }
    public Map<String, Double> getPlantAverages() {
        return plantReadings.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .mapToDouble(r -> r.actualOutput)
                                .average()
                                .orElse(0.0)
                ));
    }
}

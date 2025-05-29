package com.gevernova.fleetefficiencybenchmarking;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FleetEfficiencyBenchmarker {
    private TreeMap<String, List<TurbineEfficiencyData>> locationData;

    public FleetEfficiencyBenchmarker() {
        this.locationData = new TreeMap<>();
    }

    public void addTurbineData(String location, TurbineEfficiencyData data) {
        locationData.computeIfAbsent(location, k -> new ArrayList<>()).add(data);
    }

    public TurbineEfficiencyReport generateReport() throws MissingBenchmarkDataException {
        if (locationData.isEmpty()) {
            throw new MissingBenchmarkDataException("No turbine data available for benchmarking");
        }

        // Calculate average efficiency by location using Java 8 streams
        Map<String, Double> locationEfficiency = locationData.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .mapToDouble(TurbineEfficiencyData::getEfficiencyScore)
                                .average()
                                .orElse(0.0)
                ));

        // Get all turbines sorted by efficiency
        List<TurbineEfficiencyData> allTurbines = locationData.values().stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparingDouble(TurbineEfficiencyData::getEfficiencyScore).reversed())
                .collect(Collectors.toList());

        // Get top and bottom performers (top and bottom 10%)
        int topCount = Math.max(1, allTurbines.size() / 10);
        List<String> topPerformers = allTurbines.stream()
                .limit(topCount)
                .map(TurbineEfficiencyData::getTurbineId)
                .collect(Collectors.toList());

        List<String> bottomPerformers = allTurbines.stream()
                .skip(allTurbines.size() - topCount)
                .map(TurbineEfficiencyData::getTurbineId)
                .collect(Collectors.toList());

        return new TurbineEfficiencyReport.Builder()
                .reportDate(LocalDate.now())
                .reportTitle("Fleet Efficiency Benchmark Report")
                .locationEfficiency(locationEfficiency)
                .topPerformers(topPerformers)
                .bottomPerformers(bottomPerformers)
                .build();
    }

    public Map<String, Integer> getEfficiencyRanking() {
        List<TurbineEfficiencyData> sorted = locationData.values().stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparingDouble(TurbineEfficiencyData::getEfficiencyScore).reversed())
                .collect(Collectors.toList());

        Map<String, Integer> ranking = new HashMap<>();
        for (int i = 0; i < sorted.size(); i++) {
            ranking.put(sorted.get(i).getTurbineId(), i + 1);
        }
        return ranking;
    }
}

package com.gevernova.powerplantperformanceanalyzer.strategies;

import com.gevernova.powerplantperformanceanalyzer.DataCorruptionException;
import com.gevernova.powerplantperformanceanalyzer.MissingReadingException;
import com.gevernova.powerplantperformanceanalyzer.Reading;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

// Concrete strategy for Solar Plants
public class SolarPerformanceStrategy implements PerformanceCalculationStrategy {

    @Override
    public double calculateEfficiency(List<Reading> readings)
            throws DataCorruptionException, MissingReadingException {

        if (readings == null || readings.isEmpty()) {
            throw new MissingReadingException("No readings available for efficiency calculation");
        }

        double totalEfficiency = 0.0;
        int count = 0;

        for (Reading r : readings) {
            if (r.actualOutput < 0 || r.expectedOutput <= 0) {
                throw new DataCorruptionException("Invalid reading values");
            }
            totalEfficiency += (r.actualOutput / r.expectedOutput) * 100;
            count++;
        }

        return count > 0 ? totalEfficiency / count : 0.0;
    }

    @Override
    public double calculateUpTime(List<Reading> readings, Instant startTime, Instant endTime) {
        if (readings == null || readings.isEmpty() || startTime == null || endTime == null) {
            return 0.0;
        }

        long totalMinutes = Duration.between(startTime, endTime).toMinutes();
        if (totalMinutes <= 0) return 0.0;

        long operationalCount = readings.stream()
                .filter(r -> "OPERATIONAL".equalsIgnoreCase(r.status))
                .count();

        // Assuming 1 reading per minute. Adjust logic if readings are timestamped.
        return (double) operationalCount / totalMinutes * 100;
    }

    @Override
    public double calculateOutputDeviation(List<Reading> readings) {
        if (readings == null || readings.isEmpty()) return 0.0;

        double totalDeviation = readings.stream()
                .mapToDouble(r -> Math.abs(r.expectedOutput - r.actualOutput))
                .sum();

        double totalExpected = readings.stream()
                .mapToDouble(r -> r.expectedOutput)
                .sum();

        return totalExpected == 0 ? 0.0 : (totalDeviation / totalExpected * 100);
    }
}

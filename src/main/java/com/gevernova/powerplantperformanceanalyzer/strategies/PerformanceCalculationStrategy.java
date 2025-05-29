package com.gevernova.powerplantperformanceanalyzer.strategies;

import com.gevernova.powerplantperformanceanalyzer.DataCorruptionException;
import com.gevernova.powerplantperformanceanalyzer.MissingReadingException;
import com.gevernova.powerplantperformanceanalyzer.Reading;

import java.time.Instant;
import java.util.List;

// Strategy Pattern Interface for performance calculations
public interface PerformanceCalculationStrategy {
    double calculateEfficiency(List<Reading> readings) throws DataCorruptionException, MissingReadingException;
    double calculateUpTime(List<Reading> readings, Instant startTime, Instant endTime);
    double calculateOutputDeviation(List<Reading> readings);
}

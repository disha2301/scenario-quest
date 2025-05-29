package com.gevernova;

import com.gevernova.powerplantperformanceanalyzer.*;
import com.gevernova.powerplantperformanceanalyzer.strategies.SolarPerformanceStrategy;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import static org.junit.Assert.*;

public class PowerPlantAnalyzerTest {
    private PowerPlantAnalyzer analyzer;
    private static final String SOLAR_PLANT_ID = "SOLAR_001";
    private static final String WIND_PLANT_ID = "WIND_001";
    private Instant testStartTime;
    private Instant testEndTime;

    @Before
    public void setup() {
        analyzer = new PowerPlantAnalyzer(new SolarPerformanceStrategy());
        testStartTime = Instant.now();
        testEndTime = testStartTime.plus(Duration.ofHours(1));
    }

    // Test efficiency calculations
    @Test
    public void testCalculateEfficiency() throws Exception {
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(Instant.now(), 100, 85, "OPERATIONAL"));
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(Instant.now(), 100, 90, "OPERATIONAL"));

        double efficiency = analyzer.calculateEfficiency(SOLAR_PLANT_ID);
        assertEquals(87.5, efficiency, 0.01);
    }

    @Test(expected = MissingReadingException.class)
    public void testCalculateEfficiencyNoReadings() throws Exception {
        analyzer.calculateEfficiency("NON_EXISTENT");
    }

    @Test(expected = DataCorruptionException.class)
    public void testCalculateEfficiencyWithNegativeOutput() throws Exception {
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(Instant.now(), 100, -5, "OPERATIONAL"));
        analyzer.calculateEfficiency(SOLAR_PLANT_ID);
    }

    // Test uptime calculations
    @Test
    public void testCalculateUptime() {
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(testStartTime, 100, 80, "OPERATIONAL"));
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(testStartTime.plus(Duration.ofMinutes(30)), 100, 75, "MAINTENANCE"));

        double uptime = analyzer.calculateUptime(SOLAR_PLANT_ID, testStartTime, testEndTime);
        assertEquals(50.0, uptime, 0.01);
    }

    @Test
    public void testCalculateUptimeNoReadings() {
        double uptime = analyzer.calculateUptime("EMPTY_PLANT", testStartTime, testEndTime);
        assertEquals(0.0, uptime, 0.01);
    }

    @Test
    public void testCalculateUptimeFullPeriod() {
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(testStartTime, 100, 95, "OPERATIONAL"));
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(testEndTime, 100, 90, "OPERATIONAL"));

        double uptime = analyzer.calculateUptime(SOLAR_PLANT_ID, testStartTime, testEndTime);
        assertEquals(100.0, uptime, 0.01);
    }

    // Test output deviation calculations
    @Test
    public void testCalculateOutputDeviation() {
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(Instant.now(), 100, 90, "OPERATIONAL"));
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(Instant.now(), 100, 110, "OPERATIONAL"));

        double deviation = analyzer.calculateOutputDeviation(SOLAR_PLANT_ID);
        assertEquals(10.0, deviation, 0.01); // (10+10)/200 = 10%
    }

    @Test
    public void testCalculateOutputDeviationNoReadings() {
        double deviation = analyzer.calculateOutputDeviation("EMPTY_PLANT");
        assertEquals(0.0, deviation, 0.01);
    }

    // Test plant averages
    @Test
    public void testGetPlantAverages() {
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(Instant.now(), 100, 80, "OPERATIONAL"));
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(Instant.now(), 100, 90, "OPERATIONAL"));
        analyzer.addReading(WIND_PLANT_ID, new Reading(Instant.now(), 200, 150, "OPERATIONAL"));

        Map<String, Double> averages = analyzer.getPlantAverages();
        assertEquals(2, averages.size());
        assertEquals(85.0, averages.get(SOLAR_PLANT_ID), 0.01);
        assertEquals(150.0, averages.get(WIND_PLANT_ID), 0.01);
    }

    @Test
    public void testGetPlantAveragesEmpty() {
        Map<String, Double> averages = analyzer.getPlantAverages();
        assertTrue(averages.isEmpty());
    }

    // Test edge cases
    @Test
    public void testAddReadingToNewPlant() {
        String newPlantId = "NEW_PLANT_001";
        analyzer.addReading(newPlantId, new Reading(Instant.now(), 100, 85, "OPERATIONAL"));

        assertTrue(analyzer.getPlantAverages().containsKey(newPlantId));
    }

    @Test
    public void testMultipleReadingsSameTimestamp() {
        Instant timestamp = Instant.now();
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(timestamp, 100, 80, "OPERATIONAL"));
        analyzer.addReading(SOLAR_PLANT_ID, new Reading(timestamp, 100, 90, "OPERATIONAL"));

        // Should handle multiple readings with same timestamp
        assertEquals(2, analyzer.getPlantAverages().get(SOLAR_PLANT_ID).intValue());
    }
}
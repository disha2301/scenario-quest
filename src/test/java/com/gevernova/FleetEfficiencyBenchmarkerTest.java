package com.gevernova;

import com.gevernova.fleetefficiencybenchmarking.FleetEfficiencyBenchmarker;
import com.gevernova.fleetefficiencybenchmarking.TurbineEfficiencyData;
import com.gevernova.fleetefficiencybenchmarking.TurbineEfficiencyReport;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class FleetEfficiencyBenchmarkerTest {
    private FleetEfficiencyBenchmarker benchmarker;

    @Before
    public void setUp() {
        benchmarker = new FleetEfficiencyBenchmarker();

        // Add test data
        benchmarker.addTurbineData("North", new TurbineEfficiencyData.Builder()
                .turbineId("T1")
                .date(LocalDate.now())
                .runtimeHours(720)
                .maintenanceCount(2)
                .windSpeedAvg(8.5)
                .efficiencyScore(82.5)
                .build());

        benchmarker.addTurbineData("North", new TurbineEfficiencyData.Builder()
                .turbineId("T2")
                .date(LocalDate.now())
                .runtimeHours(680)
                .maintenanceCount(3)
                .windSpeedAvg(7.8)
                .efficiencyScore(78.3)
                .build());

        benchmarker.addTurbineData("South", new TurbineEfficiencyData.Builder()
                .turbineId("T3")
                .date(LocalDate.now())
                .runtimeHours(800)
                .maintenanceCount(1)
                .windSpeedAvg(9.2)
                .efficiencyScore(88.1)
                .build());
    }

    @Test
    public void testGenerateReport() throws Exception {
        TurbineEfficiencyReport report = benchmarker.generateReport();

        // Verify location averages
        Map<String, Double> locationEff = report.getLocationEfficiency();
        assertEquals(2, locationEff.size());
        assertTrue(locationEff.get("North") > 78.0 && locationEff.get("North") < 83.0);
        assertEquals(88.1, locationEff.get("South"), 0.01);

        // Verify top performers
        assertEquals(1, report.getTopPerformers().size());
        assertEquals("T3", report.getTopPerformers().get(0));
    }

    @Test
    public void testEfficiencyRanking() {
        Map<String, Integer> ranking = benchmarker.getEfficiencyRanking();
        assertEquals(3, ranking.size());
        assertEquals(1, (int) ranking.get("T3"));
        assertEquals(3, (int) ranking.get("T2"));
    }

    @Test
    public void testReportExport() throws Exception {
        TurbineEfficiencyReport report = benchmarker.generateReport();
        report.exportToCSV("test_report.csv");

        // Verify file was created
        assertTrue(Files.exists(Paths.get("test_report.csv")));

        // Clean up
        Files.deleteIfExists(Paths.get("test_report.csv"));
    }
}
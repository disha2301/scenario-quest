package com.gevernova.fleetefficiencybenchmarking;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Report class with builder pattern
public class TurbineEfficiencyReport {
    private LocalDate reportDate;
    private String reportTitle;
    private Map<String, Double> locationEfficiency;
    private List<String> topPerformers;
    private List<String> bottomPerformers;

    private TurbineEfficiencyReport(Builder builder) {
        this.reportDate = builder.reportDate;
        this.reportTitle = builder.reportTitle;
        this.locationEfficiency = builder.locationEfficiency;
        this.topPerformers = builder.topPerformers;
        this.bottomPerformers = builder.bottomPerformers;
    }

    // Getters
    public LocalDate getReportDate() { return reportDate; }
    public String getReportTitle() { return reportTitle; }
    public Map<String, Double> getLocationEfficiency() { return locationEfficiency; }
    public List<String> getTopPerformers() { return topPerformers; }
    public List<String> getBottomPerformers() { return bottomPerformers; }

    // Builder Pattern implementation
    public static class Builder {
        private LocalDate reportDate;
        private String reportTitle;
        private Map<String, Double> locationEfficiency = new HashMap<>();
        private List<String> topPerformers = new ArrayList<>();
        private List<String> bottomPerformers = new ArrayList<>();

        public Builder reportDate(LocalDate reportDate) {
            this.reportDate = reportDate;
            return this;
        }

        public Builder reportTitle(String reportTitle) {
            this.reportTitle = reportTitle;
            return this;
        }

        public Builder locationEfficiency(Map<String, Double> locationEfficiency) {
            this.locationEfficiency = locationEfficiency;
            return this;
        }

        public Builder topPerformers(List<String> topPerformers) {
            this.topPerformers = topPerformers;
            return this;
        }

        public Builder bottomPerformers(List<String> bottomPerformers) {
            this.bottomPerformers = bottomPerformers;
            return this;
        }

        public TurbineEfficiencyReport build() {
            return new TurbineEfficiencyReport(this);
        }
    }

    // Export report to CSV
    public void exportToCSV(String filename) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            writer.write("Report Title," + reportTitle + "\n");
            writer.write("Report Date," + reportDate + "\n\n");
            writer.write("Location,Average Efficiency\n");

            for (Map.Entry<String, Double> entry : locationEfficiency.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }

            writer.write("\nTop Performing Turbines\n");
            for (String turbine : topPerformers) {
                writer.write(turbine + "\n");
            }

            writer.write("\nBottom Performing Turbines\n");
            for (String turbine : bottomPerformers) {
                writer.write(turbine + "\n");
            }
        }
    }
}

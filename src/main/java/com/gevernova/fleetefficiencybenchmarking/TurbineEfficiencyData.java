package com.gevernova.fleetefficiencybenchmarking;

import java.time.LocalDate;

// Data class for turbine efficiency metrics
public class TurbineEfficiencyData {
    private String turbineId;
    private LocalDate date;
    private double runtimeHours;
    private int maintenanceCount;
    private double windSpeedAvg;
    private double efficiencyScore;

    // Private constructor for builder
    private TurbineEfficiencyData(Builder builder) {
        this.turbineId = builder.turbineId;
        this.date = builder.date;
        this.runtimeHours = builder.runtimeHours;
        this.maintenanceCount = builder.maintenanceCount;
        this.windSpeedAvg = builder.windSpeedAvg;
        this.efficiencyScore = builder.efficiencyScore;
    }

    // Getters
    public String getTurbineId() { return turbineId; }
    public LocalDate getDate() { return date; }
    public double getRuntimeHours() { return runtimeHours; }
    public int getMaintenanceCount() { return maintenanceCount; }
    public double getWindSpeedAvg() { return windSpeedAvg; }
    public double getEfficiencyScore() { return efficiencyScore; }

    // Builder Pattern implementation
    public static class Builder {
        private String turbineId;
        private LocalDate date;
        private double runtimeHours;
        private int maintenanceCount;
        private double windSpeedAvg;
        private double efficiencyScore;

        public Builder turbineId(String turbineId) {
            this.turbineId = turbineId;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder runtimeHours(double runtimeHours) {
            this.runtimeHours = runtimeHours;
            return this;
        }

        public Builder maintenanceCount(int maintenanceCount) {
            this.maintenanceCount = maintenanceCount;
            return this;
        }

        public Builder windSpeedAvg(double windSpeedAvg) {
            this.windSpeedAvg = windSpeedAvg;
            return this;
        }

        public Builder efficiencyScore(double efficiencyScore) {
            this.efficiencyScore = efficiencyScore;
            return this;
        }

        public TurbineEfficiencyData build() {
            return new TurbineEfficiencyData(this);
        }
    }
}
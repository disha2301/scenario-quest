package com.gevernova.energyassettracker;

import java.time.Instant;

// Sensor Log data model
public class SensorLog {
    private String assetId;
    private Instant timestamp;
    private double temperature;
    private double vibration;
    private double current;
    private String status; // "NORMAL", "WARNING", "CRITICAL"

    public SensorLog(String assetId, Instant timestamp, double temperature,
                     double vibration, double current, String status) {
        this.assetId = assetId;
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.vibration = vibration;
        this.current = current;
        this.status = status;
    }

    // Getters
    public String getAssetId() { return assetId; }
    public Instant getTimestamp() { return timestamp; }
    public double getTemperature() { return temperature; }
    public double getVibration() { return vibration; }
    public double getCurrent() { return current; }
    public String getStatus() { return status; }
}

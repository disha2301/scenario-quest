package com.gevernova.energyassettracker;

import com.gevernova.energyassettracker.observerpattern.FaultObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class AssetTracker {
    private Map<String, List<SensorLog>> assetLogs = new HashMap<>();
    private List<FaultObserver> observers = new ArrayList<>();
    private String logFile = "asset_tracker.log";

    // adding new sensor log for an asset
    public void addLog(SensorLog log) throws AssetOfflineException{
        if("OFFLINE".equals(log.getStatus())){
            throw new AssetOfflineException("Asset "+log.getAssetId() + " is offline");
        }

        assetLogs.computeIfAbsent(log.getAssetId(), k -> new ArrayList<>()).add(log);
    }

    // to checks for faults in recent logs using Stream API

    public void checkForFaults(String assetId, long minutes) {
        Instant cutoff = Instant.now().minusSeconds(minutes * 60);

        List<SensorLog> recentLogs = assetLogs.getOrDefault(assetId, Collections.emptyList())
                .stream()
                .filter(log -> log.getTimestamp().isAfter(cutoff))
                .collect(Collectors.toList());
    }
    public void registerObserver(FaultObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String assetId, String message) {
        observers.forEach(o -> o.update(assetId, message));
        logToFile("Fault detected: " + message);
    }

    private void logToFile(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(logFile, true))) {
            out.println(Instant.now() + " - " + message);
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}

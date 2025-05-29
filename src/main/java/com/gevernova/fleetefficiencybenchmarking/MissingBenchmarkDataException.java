package com.gevernova.fleetefficiencybenchmarking;

// Custom exception for missing data
public class MissingBenchmarkDataException extends Exception {
    public MissingBenchmarkDataException(String message) {
        super(message);
    }
}

package com.gevernova.energyassettracker;

// Custom exceptions
public class AssetOfflineException extends Exception {
    public AssetOfflineException(String message) { super(message); }
}
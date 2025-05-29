package com.gevernova.energyassettracker.observerpattern;

public interface FaultObserver {
    void update(String assetId, String message);
}

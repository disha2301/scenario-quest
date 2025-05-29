package com.gevernova.energyassettracker;

import com.gevernova.energyassettracker.interfacesegragation.CurrentMonitor;
import com.gevernova.energyassettracker.interfacesegragation.TemperatureMonitor;

public class Transformer implements TemperatureMonitor, CurrentMonitor {
    private static double MAX_TEMP = 90.0;
    private static double MAX_CURRENT = 500.0;

    @Override
    public void checkTemperature(SensorLog log) throws FaultDetectedException {
        if(log.getTemperature() > MAX_TEMP){
            throw new FaultDetectedException(
                    String.format("Transformer %s overheating: %.1f°C",
                            log.getAssetId(), log.getTemperature()));
        }
    }
    @Override
    public void checkCurrent(SensorLog log) throws FaultDetectedException{
        if(log.getCurrent() > MAX_CURRENT){
            throw new FaultDetectedException(
                    String.format("Transformer %s current surge: %.1fA",
                            log.getAssetId(), log.getCurrent()));
        }
    }
}

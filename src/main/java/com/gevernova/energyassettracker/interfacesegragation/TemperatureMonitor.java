package com.gevernova.energyassettracker.interfacesegragation;

import com.gevernova.energyassettracker.FaultDetectedException;
import com.gevernova.energyassettracker.SensorLog;

public interface TemperatureMonitor {
    void checkTemperature(SensorLog log) throws FaultDetectedException;
}

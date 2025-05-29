package com.gevernova.energyassettracker.interfacesegragation;

import com.gevernova.energyassettracker.FaultDetectedException;
import com.gevernova.energyassettracker.SensorLog;

public interface CurrentMonitor {
    void checkCurrent(SensorLog log) throws FaultDetectedException;
}

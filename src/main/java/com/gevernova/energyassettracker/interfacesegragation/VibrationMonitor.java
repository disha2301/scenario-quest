package com.gevernova.energyassettracker.interfacesegragation;

import com.gevernova.energyassettracker.FaultDetectedException;
import com.gevernova.energyassettracker.SensorLog;

interface VibrationMonitor {
    void checkVibration(SensorLog log) throws FaultDetectedException;
}

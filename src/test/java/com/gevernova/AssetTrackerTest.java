package com.gevernova;

import com.gevernova.energyassettracker.AssetOfflineException;
import com.gevernova.energyassettracker.AssetTracker;
import com.gevernova.energyassettracker.SensorLog;
import com.gevernova.energyassettracker.observerpattern.FaultObserver;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.Assert.assertTrue;

class AssetTrackerTest {
    private AssetTracker tracker;
    private TestObserver testObserver;

    @Before
    public void setUp() {
        tracker = new AssetTracker();
        testObserver = new TestObserver();
        tracker.registerObserver(testObserver);
    }
    @Test
    public void testAddLog() throws Exception {
        SensorLog log = new SensorLog("TR_001", Instant.now(), 75.0, 0.5, 300.0, "NORMAL");
        tracker.addLog(log);
        // Verify log was added (would need getter method in tracker)
    }

    @Test
    public void testTemperatureFaultDetection() {
        SensorLog log = new SensorLog("TR_003", Instant.now(), 95.0, 0.5, 300.0, "NORMAL");
        assertTrue(testObserver.lastMessage.contains("overheating"));
    }

    @Test
    public void testCurrentFaultDetection() {
        SensorLog log = new SensorLog("CB_001", Instant.now(), 30.0, 0.2, 1200.0, "NORMAL");
        assertTrue(testObserver.lastMessage.contains("overcurrent"));
    }

    // Helper test observer
    class TestObserver implements FaultObserver {
        public String lastMessage;

        @Override
        public void update(String assetId, String message) {
            this.lastMessage = message;
        }
    }
}
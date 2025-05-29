package com.gevernova;


import com.gevernova.smartgridloadbalancer.GridOverloadException;
import com.gevernova.smartgridloadbalancer.Region;
import com.gevernova.smartgridloadbalancer.SmartGridLoadBalancer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    class SmartGridLoadBalancerTest {
        private SmartGridLoadBalancer balancer;

        @BeforeEach
        void setUp() {
            balancer = new SmartGridLoadBalancer();
        }

        @Test
        void testAddRegionAndCheckLoadStatus() {
            Region r1 = new Region("TestRegion", 450, 500, false); // 90% load
            balancer.addRegion(r1);

            assertEquals(1, r1.getLoadPercentage());
            assertEquals(90, r1.getLoadPercentage());
        }
        @Test
        void testCriticalStrategyBalancing() throws GridOverloadException {
            balancer.addRegion(new Region("North", 450, 500, false)); // 90%
            balancer.addRegion(new Region("South", 850, 1000, true)); // 85%
            balancer.addRegion(new Region("West", 950, 1000, false)); // 95% — overloaded

            assertDoesNotThrow(() -> balancer.balanceLoad("critical", 200));
        }

        @Test
        void testEvenStrategyBalancing() throws GridOverloadException {
            balancer.addRegion(new Region("North", 300, 500, false));
            balancer.addRegion(new Region("South", 600, 1000, false));

            assertDoesNotThrow(() -> balancer.balanceLoad("even", 100));
        }

        @Test
        void testGridOverloadThrowsException() {
            balancer.addRegion(new Region("East", 300, 400, false));
            balancer.addRegion(new Region("Overload", 1200, 1000, false)); // 120% load

            GridOverloadException ex = assertThrows(
                    GridOverloadException.class,
                    () -> balancer.balanceLoad("critical", 50)
            );

            assertTrue(ex.getMessage().contains("Grid overload detected"));
        }
    }


    package com.gevernova.smartgridloadbalancer;

    import java.util.PriorityQueue;
    import java.util.function.Function;

    // Main Grid Load Balancer class
    public class SmartGridLoadBalancer {
        private PriorityQueue<Region> regions;
        private static final int SAFE_THRESHOLD = 90; // 90% load is considered unsafe

        // function to check if a region is overloaded
        private static final Function<Region, Boolean> isOverloaded = region -> region.getLoadPercentage() > SAFE_THRESHOLD;

        public SmartGridLoadBalancer() {
            // Initialize priority queue that sorts regions by criticality and load
            regions = new PriorityQueue<>();
        }

        public void addRegion(Region region) {
            regions.add(region);
        }

        public void balanceLoad(String strategyType, int availablePower) throws GridOverloadException {
            // Check for any regions exceeding safety thresholds
            boolean anyOverLoaded = regions.stream().anyMatch(isOverloaded::apply);

            if (anyOverLoaded) {
                System.out.println("Warning: Some regions are overloaded!");
                regions.stream()
                        .filter(isOverloaded::apply)
                        .forEach(r -> System.out.println("Overloaded: " + r));
            }
            // check total system load
            int totalCapacity = regions.stream().mapToInt(Region::getCapacity).sum();
            int totalLoad = regions.stream().mapToInt(Region::getCurrentLoad).sum();
            if (totalLoad > totalCapacity) {
                throw new GridOverloadException("Grid overload detected! Total load exceeds total capacity.");
            }
            // get the appropiate strategy from factory
            LoadBalancingStrategy strategy = LoadBalancingStrategyFactory.getStrategy(strategyType);

            // apply the strategy
            strategy.balanceLoad(regions, availablePower);

            // print final state
            System.out.println("\nFinal state:");
            regions.stream().sorted().forEach(System.out::println);
        }

        public static void main(String[] args) {
            // Create the load balancer
            SmartGridLoadBalancer balancer = new SmartGridLoadBalancer();

            // Add some regions with different loads
            balancer.addRegion(new Region("North", 450, 500, false)); // 90% load
            balancer.addRegion(new Region("South", 850, 1000, true));  // 85% load (critical)
            balancer.addRegion(new Region("East", 300, 400, false));   // 75% load
            balancer.addRegion(new Region("West", 950, 1000, false));  // 95% load (overloaded)
            try {
                // Try to balance with 200 units of available power
                System.out.println("=== Attempting to balance load ===");
                balancer.balanceLoad("critical", 200);

                // Try another strategy
                System.out.println("\n=== Trying different strategy ===");
                balancer.balanceLoad("even", 150);

                // Test overload scenario
                System.out.println("\n=== Testing overload scenario ===");
                balancer.addRegion(new Region("Central", 1200, 1000, false)); // This should trigger exception
                balancer.balanceLoad("critical", 100);

            } catch (GridOverloadException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }


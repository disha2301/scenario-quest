package com.gevernova.smartgridloadbalancer;

import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EvenDistributionStrategy implements LoadBalancingStrategy {

    @Override
    public void balanceLoad(PriorityQueue<Region> regions, int availablePower) throws GridOverloadException {
        System.out.println("Applying Even Distribution strategy...");

        // Filter overloaded regions (load > 80% capacity)
        Predicate<Region> isOverloaded = r -> r.getLoadPercentage() > 80;
        List<Region> overloadedRegions = regions.stream()
                .filter(isOverloaded)
                .collect(Collectors.toList());

        if (overloadedRegions.isEmpty()) {
            System.out.println("No overloaded regions found.");
            return;
        }

        int powerPerRegion = availablePower / overloadedRegions.size();

        for (Region region : overloadedRegions) {
            int needed = region.getCapacity() - region.getCurrentLoad();
            int toTransfer = Math.min(needed, powerPerRegion);

            if (toTransfer > 0) {
                region.updateLoad(toTransfer);
                System.out.printf("Transferred %d power to %s%n", toTransfer, region.getId());
            }
        }
    }
}

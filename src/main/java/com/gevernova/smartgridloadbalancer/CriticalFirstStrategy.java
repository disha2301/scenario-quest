package com.gevernova.smartgridloadbalancer;

import java.util.PriorityQueue;

// Concrete strategy: Prioritize critical regions first
public class CriticalFirstStrategy implements LoadBalancingStrategy{
    @Override
    public void balanceLoad(PriorityQueue<Region> regions, int availablePower) throws GridOverloadException{
        System.out.println("Applying Critical First strategy...");

        // Create a temporary queue to avoid modifying the original
        PriorityQueue<Region> tempQueue = new PriorityQueue<>(regions);

        while(!tempQueue.isEmpty() && availablePower > 0){
            Region region = tempQueue.poll();

            //calculate needed power
            int needed = region.getCapacity() - region.getCurrentLoad();
            int toTransfer = Math.min(needed, availablePower);

            if(toTransfer > 0){
                region.updateLoad(toTransfer);
                availablePower -= toTransfer;
                System.out.printf("Transferred %d power to %s%n", toTransfer, region.getId());
            }
        }
        if(availablePower > 0){
            System.out.println("Warning: Remaining power not allocated: " + availablePower);
        }
    }
}

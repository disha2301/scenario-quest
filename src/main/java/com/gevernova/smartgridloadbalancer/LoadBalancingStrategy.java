package com.gevernova.smartgridloadbalancer;

import java.util.PriorityQueue;

// Interface for load balancing strategies
interface LoadBalancingStrategy {
    void balanceLoad(PriorityQueue<Region> regions, int availablePower) throws GridOverloadException;
}

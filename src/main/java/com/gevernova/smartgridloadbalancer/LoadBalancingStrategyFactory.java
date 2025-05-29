package com.gevernova.smartgridloadbalancer;

// factory to create loading balancing strategy
public class LoadBalancingStrategyFactory {
    public static LoadBalancingStrategy getStrategy(String strategyType){
        switch(strategyType.toLowerCase()){
            case "critical":
                return new CriticalFirstStrategy();
            case "even":
                return new EvenDistributionStrategy();
            default:
                throw new IllegalArgumentException("Invalid strategy :" +strategyType);
        }
    }

}

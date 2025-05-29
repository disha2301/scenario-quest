package com.gevernova.carbonemissioncalculator;

// Custom exception for negative emissions (shouldn't happen in reality)
public class NegativeEmissionException extends Exception {
    public NegativeEmissionException(String message) {
        super(message);
    }
}
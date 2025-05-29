package com.gevernova.powerplantperformanceanalyzer;

import java.time.Instant;

// data model for plant reading
public class Reading {

    //data members
    Instant timestamp;
    public double expectedOutput;
    public double actualOutput;
    public String status;  // "OPERATIONAL", "MAINTENANCE", etc.

    // constructor
    public Reading(Instant timestamp, double expectedOutput, double actualOutput, String status){
        this.timestamp = timestamp;
        this.expectedOutput = expectedOutput;
        this.actualOutput = actualOutput;
        this.status = status;
    }
}

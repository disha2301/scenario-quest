package com.gevernova.onlinecoursemanagement;

// STEP 3 - IMPLEMENTATION OF THE GRADING INTERFACE

public class PercentageRequired implements Grading{
    @Override
    public boolean isEligible(double markPercentage){

        // minimum percentage (in double) to get eligible to get the certificate
        return markPercentage >=70.0;
    }
}

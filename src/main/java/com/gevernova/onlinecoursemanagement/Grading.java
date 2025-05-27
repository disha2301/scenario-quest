package com.gevernova.onlinecoursemanagement;

// STEP 2 - GRADING INTERFACE TO TRACK ELIGIBILITY FOR CERTIFICATE
public interface Grading {

    // eligible on the basis of marks percentage in modules
    boolean isEligible(double progressPercent);
}
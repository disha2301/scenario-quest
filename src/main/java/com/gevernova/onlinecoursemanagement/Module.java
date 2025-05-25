package com.gevernova.onlinecoursemanagement;

// STEP 1 - MODULE CLASS TO INITIALIZE THE MODULE VARIABLES

public class Module {

    //data members
    private String title; // to store the title of the module
    private int passingMarks; // the percentage it holds to get eligible

    // constructor to initialize the member variables
    public Module(String title, int passingMarks) {
        this.title = title;
        this.passingMarks = passingMarks;
    }

    //getters to get the private data
    public String getTitle() {
        return title;
    }
    public int getPassingMarks() {
        return passingMarks;
    }
}


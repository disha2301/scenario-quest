package com.gevernova.onlinecoursemanagement;

// STEP 1 - STUDENT CLASS TO INITIALIZE THE STUDENT VARIABLES

public class Student {
    // data members for id and name
    private final String id;
    private final String name;

    // constructor to initialize the data members at object creation
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters to get and return the private values
    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    // to display the data in an easy format
    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}


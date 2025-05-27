package com.gevernova.onlinecoursemanagement;

import java.util.List;

// STEP 1 - COURSE CLASS TO INITIALIZE THE COURSE VARIABLES

public class Course {

    // add the data members
    private String name;
    private List<Module> modules;

    // constructor to initialize the member variables at the time of object creation
    public Course(String name, List<Module> modules) {
        this.name = name;
        this.modules = modules;
    }

    // getters to get the private values
    public String getName(){
        return name;
    }
    public List<Module> getModules(){
        return modules;
    }
}
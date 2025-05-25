package com.gevernova.employeemanagementsystem;

public class Department {
    private final String name;

    public Department(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Invalid department name");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

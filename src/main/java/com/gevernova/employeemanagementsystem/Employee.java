package com.gevernova.employeemanagementsystem;

// Employee.java - encapsulation : Represents employee data only
public class Employee {
    // data members
    private String name;
    private double salary;
    private String department;
    private int performanceScore;

    // constructors with exception handling
    public Employee(String name, double salary, String department, int performanceScore) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be empty");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        if (department == null || department.isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty");
        }
        if (performanceScore < 0 || performanceScore > 100) {
            throw new IllegalArgumentException("Performance score must be between 0 and 100");
        }
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.performanceScore = performanceScore;
    }

    // getters
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public int getPerformanceScore() { return performanceScore; }

}

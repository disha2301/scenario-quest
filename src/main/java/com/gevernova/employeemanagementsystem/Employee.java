package com.gevernova.employeemanagementsystem;

// Employee.java
public class Employee {
    private final String name;
    private final Department department;
    private final double salary;
    private int performanceScore;

    public Employee(String name, Department department, double salary) {
        if (name == null || department == null || salary < 0) {
            throw new IllegalArgumentException("Invalid Employee parameters");
        }
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.performanceScore = 0;
    }

    public String getName() { return name; }
    public Department getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getPerformanceScore() { return performanceScore; }
    public void setPerformanceScore(int score) {
        if (score < 0 || score > 100) throw new IllegalArgumentException("Score must be 0-100");
        this.performanceScore = score;
    }
}

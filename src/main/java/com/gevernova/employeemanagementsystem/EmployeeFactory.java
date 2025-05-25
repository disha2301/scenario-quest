package com.gevernova.employeemanagementsystem;

public class EmployeeFactory {
    public static Employee createEmployee(String name, Department dept, double salary) {
        return new Employee(name, dept, salary);
    }
}

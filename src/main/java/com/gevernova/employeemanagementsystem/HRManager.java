package com.gevernova.employeemanagementsystem;

import java.util.*;
import java.util.stream.Collectors;

public class HRManager {
    private final List<Employee> employees = new ArrayList<>();

    // Add employee to the list
    public void addEmployee(Employee e) {
        if (e == null) throw new IllegalArgumentException("Employee cannot be null");
        employees.add(e);
    }

    // Return an unmodifiable list of all employees
    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    // Return employees sorted by salary (ascending)
    public List<Employee> sortBySalary() {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .collect(Collectors.toList());
    }

    // Group employees by department
    public Map<Department, List<Employee>> groupByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    // Return employees belonging to a specific department
    public List<Employee> filterByDepartment(Department dept) {
        if (dept == null) throw new IllegalArgumentException("Department cannot be null");
        return employees.stream()
                .filter(e -> e.getDepartment().equals(dept))
                .collect(Collectors.toList());
    }
}

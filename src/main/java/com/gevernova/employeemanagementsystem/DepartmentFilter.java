package com.gevernova.employeemanagementsystem;

import java.util.List;
import java.util.stream.Collectors;

// DepartmentFilter.java - SRP: Filter employees by department only
public class DepartmentFilter implements EmployeeFilter{
    private List<String> validDepartment;

    //constructor
    public DepartmentFilter(List<String> validDepartment){
        this.validDepartment = validDepartment;
    }

    @Override
    public List<Employee> filter(List<Employee> employees, String department) throws InvalidDepartmentException{
        if(!validDepartment.contains(department)){
            throw new InvalidDepartmentException("Invalid department: " + department);
        }
        // Filter employees belonging to the specified department
        return employees.stream()
                .filter(e->e.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }
}

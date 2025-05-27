package com.gevernova.employeemanagementsystem;
// Interfaces for DIP - abstractions for sorting and filtering
import java.util.List;

public interface EmployeeFilter {
    List<Employee> filter(List<Employee> employees, String criteria) throws InvalidDepartmentException;
}

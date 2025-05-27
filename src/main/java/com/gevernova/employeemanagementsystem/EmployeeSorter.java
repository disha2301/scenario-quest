package com.gevernova.employeemanagementsystem;
// Interfaces for DIP - abstractions for sorting and filtering
import java.util.List;

public interface EmployeeSorter {
        void sort(List<Employee> employees);
}

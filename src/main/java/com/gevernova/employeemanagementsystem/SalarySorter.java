package com.gevernova.employeemanagementsystem;
// SalarySorter.java - SRP: Sort employees by salary only
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SalarySorter implements EmployeeSorter{
    @Override
    public void sort(List<Employee> employees){
        // Sort employees by salary in descending order
        Collections.sort(employees, Comparator.comparingDouble(Employee::getSalary).reversed());
    }
}

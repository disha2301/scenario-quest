package com.gevernova.employeemanagementsystem;

import java.util.List;

// HRManager.java - High-level module depends on abstractions (DIP)
public class HRManager {
    // data members
    private EmployeeSorter sorter;
    private EmployeeFilter filter;

    // constructor
    public HRManager(EmployeeSorter sorter, EmployeeFilter filter) {
        this.sorter = sorter;
        this.filter = filter;
    }

    // Sort employees using injected sorter implementation
    public void sortEmployeeBySalary(List<Employee> employeeList){
        sorter.sort(employeeList);
    }

    // Filter employees by department using injected filter implementation
    public List<Employee> filterByDept(List<Employee> employeeList, String dept) throws InvalidDepartmentException {
        return filter.filter(employeeList,dept);
    }

}

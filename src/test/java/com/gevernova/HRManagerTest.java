package com.gevernova;

import com.gevernova.employeemanagementsystem.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HRManagerTest {
    private List<Employee> employees;
    private HRManager hrManager;

    @Before
    public void setup() {
        employees = new ArrayList<>();
        employees.add(new Employee("Disha", 70000, "Engineering", 85));
        employees.add(new Employee("Ahad", 50000, "Marketing", 75));
        employees.add(new Employee("Sakshi", 60000, "Engineering", 90));
        employees.add(new Employee("Arushi", 55000, "HR", 65));
        List<String> validDept = Arrays.asList("Engineering", "Marketing", "HR");
        hrManager = new HRManager(new SalarySorter(), new DepartmentFilter(validDept));
    }

    @Test
    public void testSortEmployeesBySalary() {
        hrManager.sortEmployeeBySalary(employees);
        assertEquals("Disha", employees.get(0).getName());
        assertEquals("Sakshi", employees.get(1).getName());
        assertEquals("Arushi", employees.get(2).getName());
        assertEquals("Ahad", employees.get(3).getName());
    }

    @Test
    public void testFilterEmployeesByDepartment_Valid() throws InvalidDepartmentException {
        List<Employee> engineeringEmployees = hrManager.filterByDept(employees, "Engineering");
        assertEquals(2, engineeringEmployees.size());
        assertTrue(engineeringEmployees.stream().allMatch(e -> e.getDepartment().equalsIgnoreCase("Engineering")));
    }

    @Test(expected = InvalidDepartmentException.class)
    public void testFilterEmployeesByDepartment_Invalid() throws InvalidDepartmentException {
        hrManager.filterByDept(employees, "Finance");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmployeeCreationWithInvalidSalary() {
        new Employee("Dishayadav", -1000, "Engineering", 80);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmployeeCreationWithInvalidPerformanceScore() {
        new Employee("Eve", 60000, "Engineering", 150);
    }
}


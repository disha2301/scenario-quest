package com.gevernova;

import com.gevernova.employeemanagementsystem.Department;
import com.gevernova.employeemanagementsystem.Employee;
import com.gevernova.employeemanagementsystem.EmployeeFactory;
import com.gevernova.employeemanagementsystem.HRManager;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HRManagerTest {

    @Test
    public void testAddAndRetrieveEmployees() {
        HRManager hr = new HRManager();
        Department it = new Department("IT");
        Employee disha = EmployeeFactory.createEmployee("Disha", it, 50000);

        hr.addEmployee(disha);
        assertEquals(1, hr.getEmployees().size());
        assertEquals("Disha", hr.getEmployees().get(0).getName());
    }

    @Test
    public void testSortBySalary() {
        HRManager hr = new HRManager();
        Department sales = new Department("Sales");

        hr.addEmployee(EmployeeFactory.createEmployee("Ahad", sales, 70000));
        hr.addEmployee(EmployeeFactory.createEmployee("Sakshi", sales, 60000));

        List<Employee> sorted = hr.sortBySalary();
        assertEquals("Sakshi", sorted.get(0).getName());
        assertEquals("Ahad", sorted.get(1).getName());
    }

    @Test
    public void testGroupByDepartment() {
        HRManager hr = new HRManager();
        Department hrDept = new Department("HR");
        Department it = new Department("IT");

        hr.addEmployee(EmployeeFactory.createEmployee("Arushi", hrDept, 40000));
        hr.addEmployee(EmployeeFactory.createEmployee("CRR", it, 55000));

        Map<Department, List<Employee>> grouped = hr.groupByDepartment();
        assertEquals(1, grouped.get(hrDept).size());
        assertEquals(1, grouped.get(it).size());
    }

    @Test
    public void testFilterByDepartmentPositive() {
        HRManager hr = new HRManager();
        Department finance = new Department("Finance");
        Employee ahad = EmployeeFactory.createEmployee("Ahad", finance, 45000);
        hr.addEmployee(ahad);

        List<Employee> result = hr.filterByDepartment(finance);
        assertEquals(1, result.size());
        assertEquals("Ahad", result.get(0).getName());
    }

    @Test
    public void testFilterByDepartmentNegative() {
        HRManager hr = new HRManager();
        Department finance = new Department("Finance");
        Department marketing = new Department("Marketing");

        hr.addEmployee(EmployeeFactory.createEmployee("Sakshi", finance, 48000));
        List<Employee> result = hr.filterByDepartment(marketing);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testAddNullEmployeeThrowsException() {
        HRManager hr = new HRManager();
        assertThrows(IllegalArgumentException.class, () -> hr.addEmployee(null));
    }

    @Test
    public void testFilterByNullDepartmentThrowsException() {
        HRManager hr = new HRManager();
        assertThrows(IllegalArgumentException.class, () -> hr.filterByDepartment(null));
    }

    @Test
    public void testInvalidPerformanceScoreThrowsException() {
        Employee crr = new Employee("CRR", new Department("Admin"), 30000);
        assertThrows(IllegalArgumentException.class, () -> crr.setPerformanceScore(-1));
        assertThrows(IllegalArgumentException.class, () -> crr.setPerformanceScore(101));
    }
}

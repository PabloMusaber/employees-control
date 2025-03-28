package com.challenge.Employees.controllers;

import com.challenge.Employees.services.employee.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.challenge.Employees.dtos.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salary-segments")
    public ResponseEntity<List<SalarySegmentDTO>> getSalarySegmentDistribution() {
        return ResponseEntity.ok(employeeService.getSalarySegmentDistribution());
    }

    @GetMapping("/salary-segments-by-department")
    public ResponseEntity<List<DepartmentSalarySegmentDTO>> getSalarySegmentByDepartment() {
        return ResponseEntity.ok(employeeService.getSalarySegmentByDepartment());
    }

    @GetMapping("/highest-paid-per-department")
    public ResponseEntity<List<HighestPaidEmployeeDTO>> getHighestPaidEmployeePerDepartment() {
        return ResponseEntity.ok(employeeService.getHighestPaidEmployeePerDepartment());
    }

    @GetMapping("/veteran-managers")
    public ResponseEntity<List<VeteranManagerDTO>> getVeteranManagers() {
        return ResponseEntity.ok(employeeService.getVeteranManagers());
    }

    @GetMapping("/departments-with-10-plus-employees")
    public ResponseEntity<List<DepartmentAverageSalaryDTO>> getDepartmentsWithMoreThan10Employees() {
        return ResponseEntity.ok(employeeService.getDepartmentsWithMoreThan10Employees());
    }

    @GetMapping("/country-employee-stats")
    public ResponseEntity<List<CountryEmployeeStatsDTO>> getCountryEmployeeStatistics() {
        return ResponseEntity.ok(employeeService.getCountryEmployeeStatistics());
    }
}
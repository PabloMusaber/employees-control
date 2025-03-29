package com.challenge.Employees.controllers;

import com.challenge.Employees.services.employee.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;

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

    @Operation(summary = "Obtiene los segmentos de salario A, B y C")
    @GetMapping("/salary-segments")
    public ResponseEntity<List<SalarySegmentDTO>> getSalarySegmentDistribution() {
        return ResponseEntity.ok(employeeService.getSalarySegmentDistribution());
    }

    @Operation(summary = "Obtiene los segmentos de salario A, B y C por departamento")
    @GetMapping("/salary-segments-by-department")
    public ResponseEntity<List<DepartmentSalarySegmentDTO>> getSalarySegmentByDepartment() {
        return ResponseEntity.ok(employeeService.getSalarySegmentByDepartment());
    }

    @Operation(summary = "Obtiene los empleados mejores pagos de cada departamento")
    @GetMapping("/highest-paid-per-department")
    public ResponseEntity<List<HighestPaidEmployeeDTO>> getHighestPaidEmployeePerDepartment() {
        return ResponseEntity.ok(employeeService.getHighestPaidEmployeePerDepartment());
    }

    @Operation(summary = "Obtiene los managers con más de 15 años de antigüedad.")
    @GetMapping("/veteran-managers")
    public ResponseEntity<List<VeteranManagerDTO>> getVeteranManagers() {
        return ResponseEntity.ok(employeeService.getVeteranManagers());
    }

    @Operation(summary = "Obtiene el salario promedio de todos los departamentos con más de 10 empleados")
    @GetMapping("/departments-with-10-plus-employees")
    public ResponseEntity<List<DepartmentAverageSalaryDTO>> getDepartmentsWithMoreThan10Employees() {
        return ResponseEntity.ok(employeeService.getDepartmentsWithMoreThan10Employees());
    }

    @Operation(summary = "Obtiene información de empleados agrupada por país")
    @GetMapping("/country-employee-stats")
    public ResponseEntity<List<CountryEmployeeStatsDTO>> getCountryEmployeeStatistics() {
        return ResponseEntity.ok(employeeService.getCountryEmployeeStatistics());
    }
}
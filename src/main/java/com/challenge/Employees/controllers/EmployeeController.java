package com.challenge.Employees.controllers;

import com.challenge.Employees.dtos.CreateEmployeeDTO;
import com.challenge.Employees.dtos.EmployeeResponseDTO;
import com.challenge.Employees.services.employee.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Registra un empleado")
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return ResponseEntity.ok(employeeService.createEmployee(createEmployeeDTO));
    }

    @Operation(summary = "Obtiene un empleado a partir de su id")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @Operation(summary = "Obtiene todos los empleados")
    @GetMapping
    public ResponseEntity<EmployeeResponseDTO> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @Operation(summary = "Actualiza un empleado")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, createEmployeeDTO));
    }

    @Operation(summary = "Elimina un empleado a partir de su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }

    @Operation(summary = "Obtiene los segmentos de salario A, B y C")
    @GetMapping("/salary-segments")
    public ResponseEntity<EmployeeResponseDTO> getSalarySegmentDistribution() {
        return ResponseEntity.ok(employeeService.getSalarySegmentDistribution());
    }

    @Operation(summary = "Obtiene los segmentos de salario A, B y C por departamento")
    @GetMapping("/salary-segments-by-department")
    public ResponseEntity<EmployeeResponseDTO> getSalarySegmentByDepartment() {
        return ResponseEntity.ok(employeeService.getSalarySegmentByDepartment());
    }

    @Operation(summary = "Obtiene los empleados mejores pagos de cada departamento")
    @GetMapping("/highest-paid")
    public ResponseEntity<EmployeeResponseDTO> getHighestPaidEmployeePerDepartment() {
        return ResponseEntity.ok(employeeService.getHighestPaidEmployeePerDepartment());
    }

    @Operation(summary = "Obtiene los managers con más de 15 años de antigüedad")
    @GetMapping("/veteran-managers")
    public ResponseEntity<EmployeeResponseDTO> getVeteranManagers() {
        return ResponseEntity.ok(employeeService.getVeteranManagers());
    }

    @Operation(summary = "Obtiene el salario promedio de todos los departamentos con más de 10 empleados")
    @GetMapping("/departments-more-than-10")
    public ResponseEntity<EmployeeResponseDTO> getDepartmentsWithMoreThan10Employees() {
        return ResponseEntity.ok(employeeService.getDepartmentsWithMoreThan10Employees());
    }

    @Operation(summary = "Obtiene información de empleados agrupada por país")
    @GetMapping("/country-statistics")
    public ResponseEntity<EmployeeResponseDTO> getCountryEmployeeStatistics() {
        return ResponseEntity.ok(employeeService.getCountryEmployeeStatistics());
    }
}
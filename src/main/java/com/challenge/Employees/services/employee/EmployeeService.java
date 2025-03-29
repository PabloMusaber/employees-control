package com.challenge.Employees.services.employee;

import com.challenge.Employees.dtos.CreateEmployeeDTO;
import com.challenge.Employees.dtos.EmployeeResponseDTO;

public interface EmployeeService {
    // CRUD
    EmployeeResponseDTO createEmployee(CreateEmployeeDTO createEmployeeDTO);
    EmployeeResponseDTO getEmployeeById(Long id);
    EmployeeResponseDTO getAllEmployees();
    EmployeeResponseDTO updateEmployee(Long id, CreateEmployeeDTO createEmployeeDTO);
    EmployeeResponseDTO deleteEmployee(Long id);

    // Custom Queries
    EmployeeResponseDTO getSalarySegmentDistribution();
    EmployeeResponseDTO getSalarySegmentByDepartment();
    EmployeeResponseDTO getHighestPaidEmployeePerDepartment();
    EmployeeResponseDTO getVeteranManagers();
    EmployeeResponseDTO getDepartmentsWithMoreThan10Employees();
    EmployeeResponseDTO getCountryEmployeeStatistics();
}
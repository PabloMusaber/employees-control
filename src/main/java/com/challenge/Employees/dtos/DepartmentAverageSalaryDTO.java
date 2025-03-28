package com.challenge.Employees.dtos;

public record DepartmentAverageSalaryDTO(
                Long departmentId,
                String departmentName,
                Double averageSalary,
                Long employeeCount) {
}
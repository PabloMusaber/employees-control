package com.challenge.Employees.dtos;

public record DepartmentSalarySegmentDTO(
        String departmentName,
        String salarySegment,
        Long employeeCount) {
}
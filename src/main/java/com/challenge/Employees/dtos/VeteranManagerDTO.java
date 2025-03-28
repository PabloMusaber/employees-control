package com.challenge.Employees.dtos;

public record VeteranManagerDTO(
        Long employeeId,
        String firstName,
        String lastName,
        String hireDate,
        String departmentName,
        String jobTitle) {
}
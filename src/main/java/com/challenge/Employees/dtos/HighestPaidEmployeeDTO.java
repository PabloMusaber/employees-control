package com.challenge.Employees.dtos;

public record HighestPaidEmployeeDTO(
                String departmentName,
                Long departmentId,
                Long employeeId,
                String firstName,
                String lastName,
                String email,
                String phoneNumber,
                Double salary) {
}
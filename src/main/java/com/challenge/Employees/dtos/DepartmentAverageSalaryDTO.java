package com.challenge.Employees.dtos;

import lombok.Data;

@Data
public class DepartmentAverageSalaryDTO {
    private Long departmentId;
    private String departmentName;
    private Double averageSalary;
    private Long employeeCount;
}
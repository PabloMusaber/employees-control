package com.challenge.Employees.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReadEmployeeDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private Double salary;
    private String jobTitle;
    private String departmentName;
}
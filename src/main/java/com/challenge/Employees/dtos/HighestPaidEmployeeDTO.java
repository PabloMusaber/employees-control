package com.challenge.Employees.dtos;

import lombok.Data;

@Data
public class HighestPaidEmployeeDTO {
    private String departmentName;
    private Long departmentId;
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Double salary;
}
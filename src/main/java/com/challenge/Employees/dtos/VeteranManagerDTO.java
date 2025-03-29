package com.challenge.Employees.dtos;

import lombok.Data;

@Data
public class VeteranManagerDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String hireDate;
    private String departmentName;
    private String jobTitle;
}
package com.challenge.Employees.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CountryEmployeeStatsDTO {
    private String countryName;
    private Long employeeCount;
    private Double averageSalary;
    private Double highestSalary;
    private Double lowestSalary;
    private BigDecimal averageYearsOfService;
}
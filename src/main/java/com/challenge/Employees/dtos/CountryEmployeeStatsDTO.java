package com.challenge.Employees.dtos;

import java.math.BigDecimal;

public record CountryEmployeeStatsDTO(
                String countryName,
                Long employeeCount,
                Double averageSalary,
                Double highestSalary,
                Double lowestSalary,
                BigDecimal averageYearsOfService) {
}
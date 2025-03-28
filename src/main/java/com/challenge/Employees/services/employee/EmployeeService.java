package com.challenge.Employees.services.employee;

import java.util.List;
import com.challenge.Employees.dtos.*;

public interface EmployeeService {
    List<SalarySegmentDTO> getSalarySegmentDistribution();

    List<DepartmentSalarySegmentDTO> getSalarySegmentByDepartment();

    List<HighestPaidEmployeeDTO> getHighestPaidEmployeePerDepartment();

    List<VeteranManagerDTO> getVeteranManagers();

    List<DepartmentAverageSalaryDTO> getDepartmentsWithMoreThan10Employees();

    List<CountryEmployeeStatsDTO> getCountryEmployeeStatistics();
}
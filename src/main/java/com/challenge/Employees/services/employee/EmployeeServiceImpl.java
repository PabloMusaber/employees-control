package com.challenge.Employees.services.employee;

import com.challenge.Employees.repositories.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.challenge.Employees.dtos.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

        private final EmployeeRepository employeeRepository;

        public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
                this.employeeRepository = employeeRepository;
        }

        @Override
        public List<SalarySegmentDTO> getSalarySegmentDistribution() {
                return employeeRepository.getSalarySegmentDistribution().stream()
                                .map(result -> new SalarySegmentDTO(
                                                (String) result[0],
                                                (Long) result[1]))
                                .collect(Collectors.toList());
        }

        @Override
        public List<DepartmentSalarySegmentDTO> getSalarySegmentByDepartment() {
                return employeeRepository.getSalarySegmentByDepartment().stream()
                                .map(result -> new DepartmentSalarySegmentDTO(
                                                (String) result[0],
                                                (String) result[1],
                                                (Long) result[2]))
                                .collect(Collectors.toList());
        }

        @Override
        public List<HighestPaidEmployeeDTO> getHighestPaidEmployeePerDepartment() {
                return employeeRepository.getHighestPaidEmployeePerDepartment().stream()
                                .map(result -> new HighestPaidEmployeeDTO(
                                                (String) result[0],
                                                (Long) result[1],
                                                (Long) result[2],
                                                (String) result[3],
                                                (String) result[4],
                                                (String) result[5],
                                                (String) result[6],
                                                (Double) result[7]))
                                .collect(Collectors.toList());
        }

        @Override
        public List<VeteranManagerDTO> getVeteranManagers() {
                return employeeRepository.getVeteranManagers().stream()
                                .map(result -> new VeteranManagerDTO(
                                                (Long) result[0],
                                                (String) result[1],
                                                (String) result[2],
                                                result[3].toString(),
                                                (String) result[4],
                                                (String) result[5]))
                                .collect(Collectors.toList());
        }

        @Override
        public List<DepartmentAverageSalaryDTO> getDepartmentsWithMoreThan10Employees() {
                return employeeRepository.getDepartmentsWithMoreThan10Employees().stream()
                                .map(result -> new DepartmentAverageSalaryDTO(
                                                (Long) result[0],
                                                (String) result[1],
                                                (Double) result[2],
                                                (Long) result[3]))
                                .collect(Collectors.toList());
        }

        @Override
        public List<CountryEmployeeStatsDTO> getCountryEmployeeStatistics() {
                return employeeRepository.getCountryEmployeeStatistics().stream()
                                .map(result -> new CountryEmployeeStatsDTO(
                                                (String) result[0],
                                                (Long) result[1],
                                                (Double) result[2],
                                                (Double) result[3],
                                                (Double) result[4],
                                                (BigDecimal) result[5]))
                                .collect(Collectors.toList());
        }
}
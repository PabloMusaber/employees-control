package com.challenge.Employees.services.employee;

import com.challenge.Employees.dtos.*;
import com.challenge.Employees.entities.Department;
import com.challenge.Employees.entities.Employee;
import com.challenge.Employees.repositories.DepartmentRepository;
import com.challenge.Employees.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public EmployeeResponseDTO createEmployee(CreateEmployeeDTO createEmployeeDTO) {
        try {
            Department department = departmentRepository.findById(createEmployeeDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            Employee employee = new Employee();
            BeanUtils.copyProperties(createEmployeeDTO, employee);
            employee.setDepartment(department);

            Employee savedEmployee = employeeRepository.save(employee);
            ReadEmployeeDTO readEmployeeDTO = convertToReadDTO(savedEmployee);
            return EmployeeResponseDTO.success("Employee created successfully", readEmployeeDTO);
        } catch (Exception e) {
            return EmployeeResponseDTO.error("Error creating employee: " + e.getMessage());
        }
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            ReadEmployeeDTO readEmployeeDTO = convertToReadDTO(employee);
            return EmployeeResponseDTO.success("Employee retrieved successfully", readEmployeeDTO);
        } catch (Exception e) {
            return EmployeeResponseDTO.error("Error retrieving employee: " + e.getMessage());
        }
    }

    @Override
    public EmployeeResponseDTO getAllEmployees() {
        try {
            List<ReadEmployeeDTO> employees = employeeRepository.findAll().stream()
                    .map(this::convertToReadDTO)
                    .collect(Collectors.toList());
            return EmployeeResponseDTO.success("Employees retrieved successfully", employees);
        } catch (Exception e) {
            return EmployeeResponseDTO.error("Error retrieving employees: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public EmployeeResponseDTO updateEmployee(Long id, CreateEmployeeDTO createEmployeeDTO) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            Department department = departmentRepository.findById(createEmployeeDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            BeanUtils.copyProperties(createEmployeeDTO, employee);
            employee.setDepartment(department);

            Employee updatedEmployee = employeeRepository.save(employee);
            ReadEmployeeDTO readEmployeeDTO = convertToReadDTO(updatedEmployee);
            return EmployeeResponseDTO.success("Employee updated successfully", readEmployeeDTO);
        } catch (Exception e) {
            return EmployeeResponseDTO.error("Error updating employee: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public EmployeeResponseDTO deleteEmployee(Long id) {
        try {
            if (!employeeRepository.existsById(id)) {
                return EmployeeResponseDTO.error("Employee not found");
            }
            employeeRepository.deleteById(id);
            return EmployeeResponseDTO.success("Employee deleted successfully", null);
        } catch (Exception e) {
            return EmployeeResponseDTO.error("Error deleting employee: " + e.getMessage());
        }
    }

    private ReadEmployeeDTO convertToReadDTO(Employee employee) {
        ReadEmployeeDTO dto = new ReadEmployeeDTO();
        BeanUtils.copyProperties(employee, dto);
        if (employee.getDepartment() != null) {
            dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        }
        return dto;
    }

    @Override
    public EmployeeResponseDTO getSalarySegmentDistribution() {
        try {
            List<Object[]> results = employeeRepository.getSalarySegmentDistribution();
            List<SalarySegmentDTO> dtos = results.stream()
                    .map(result -> new SalarySegmentDTO(
                            (String) result[0],
                            ((Number) result[1]).longValue()))
                    .collect(Collectors.toList());
            return EmployeeResponseDTO.success("Salary segment distribution retrieved successfully", dtos);
        } catch (Exception e) {
            return EmployeeResponseDTO.error("Error retrieving salary segment distribution: " + e.getMessage());
        }
    }

    @Override
    public EmployeeResponseDTO getSalarySegmentByDepartment() {
        try {
            List<Object[]> results = employeeRepository.getSalarySegmentByDepartment();
            List<DepartmentSalarySegmentDTO> dtos = results.stream()
                    .map(result -> new DepartmentSalarySegmentDTO(
                            (String) result[0],
                            (String) result[1],
                            ((Number) result[2]).longValue()))
                    .collect(Collectors.toList());
            return EmployeeResponseDTO.success("Department salary segments retrieved successfully", dtos);
        } catch (Exception e) {
            return EmployeeResponseDTO.error("Error retrieving department salary segments: " + e.getMessage());
        }
    }

    @Override
    public EmployeeResponseDTO getHighestPaidEmployeePerDepartment() {
        try {
            List<Object[]> results = employeeRepository.getHighestPaidEmployeePerDepartment();
            List<HighestPaidEmployeeDTO> dtos = results.stream()
                    .map(result -> {
                        HighestPaidEmployeeDTO dto = new HighestPaidEmployeeDTO();
                        dto.setDepartmentName((String) result[0]);
                        dto.setDepartmentId(((Number) result[1]).longValue());
                        dto.setEmployeeId(((Number) result[2]).longValue());
                        dto.setFirstName((String) result[3]);
                        dto.setLastName((String) result[4]);
                        dto.setEmail((String) result[5]);
                        dto.setPhoneNumber((String) result[6]);
                        dto.setSalary((Double) result[7]);
                        return dto;
                    })
                    .collect(Collectors.toList());
            return EmployeeResponseDTO.success("Highest paid employees retrieved successfully", dtos);
        } catch (Exception e) {
            return EmployeeResponseDTO.error("Error retrieving highest paid employees: " + e.getMessage());
        }
    }

    @Override
    public EmployeeResponseDTO getVeteranManagers() {
        try {
            List<Object[]> results = employeeRepository.getVeteranManagers();
            List<VeteranManagerDTO> dtos = results.stream()
                    .map(result -> {
                        VeteranManagerDTO dto = new VeteranManagerDTO();
                        dto.setEmployeeId(((Number) result[0]).longValue());
                        dto.setFirstName((String) result[1]);
                        dto.setLastName((String) result[2]);
                        dto.setHireDate(result[3].toString());
                        dto.setDepartmentName((String) result[4]);
                        dto.setJobTitle((String) result[5]);
                        return dto;
                    })
                    .collect(Collectors.toList());
            return EmployeeResponseDTO.success("Veteran managers retrieved successfully", dtos);
        } catch (Exception e) {
            return EmployeeResponseDTO.error("Error retrieving veteran managers: " + e.getMessage());
        }
    }

    @Override
    public EmployeeResponseDTO getDepartmentsWithMoreThan10Employees() {
        try {
            List<Object[]> results = employeeRepository.getDepartmentsWithMoreThan10Employees();
            List<DepartmentAverageSalaryDTO> dtos = results.stream()
                    .map(result -> {
                        DepartmentAverageSalaryDTO dto = new DepartmentAverageSalaryDTO();
                        dto.setDepartmentId(((Number) result[0]).longValue());
                        dto.setDepartmentName((String) result[1]);
                        dto.setAverageSalary((Double) result[2]);
                        dto.setEmployeeCount(((Number) result[3]).longValue());
                        return dto;
                    })
                    .collect(Collectors.toList());
            return EmployeeResponseDTO.success("Departments with more than 10 employees retrieved successfully", dtos);
        } catch (Exception e) {
            return EmployeeResponseDTO.error("Error retrieving departments: " + e.getMessage());
        }
    }

    @Override
    public EmployeeResponseDTO getCountryEmployeeStatistics() {
        try {
            List<Object[]> results = employeeRepository.getCountryEmployeeStatistics();
            List<CountryEmployeeStatsDTO> dtos = results.stream()
                    .map(result -> {
                        CountryEmployeeStatsDTO dto = new CountryEmployeeStatsDTO();
                        dto.setCountryName((String) result[0]);
                        dto.setEmployeeCount(((Number) result[1]).longValue());
                        dto.setAverageSalary((Double) result[2]);
                        dto.setHighestSalary((Double) result[3]);
                        dto.setLowestSalary((Double) result[4]);
                        dto.setAverageYearsOfService((BigDecimal) result[5]);
                        return dto;
                    })
                    .collect(Collectors.toList());
            return EmployeeResponseDTO.success("Country employee statistics retrieved successfully", dtos);
        } catch (Exception e) {
            return EmployeeResponseDTO.error("Error retrieving country statistics: " + e.getMessage());
        }
    }
}
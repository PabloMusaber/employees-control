package com.challenge.Employees.repositories;

import com.challenge.Employees.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

        @Query(value = "SELECT " +
                        "CASE " +
                        "WHEN salary < 3500 THEN 'SEGMENTO A' " +
                        "WHEN salary >= 3500 AND salary < 8000 THEN 'SEGMENTO B' " +
                        "WHEN salary >= 8000 THEN 'SEGMENTO C' " +
                        "END AS salarySegment, " +
                        "COUNT(*) AS employeeCount " +
                        "FROM employees " +
                        "GROUP BY salarySegment " +
                        "ORDER BY salarySegment", nativeQuery = true)
        List<Object[]> getSalarySegmentDistribution();

        @Query(value = "SELECT " +
                        "    d.department_name AS DepartmentName, " +
                        "    CASE " +
                        "        WHEN e.salary < 3500 THEN 'SEGMENTO A' " +
                        "        WHEN e.salary >= 3500 AND e.salary < 8000 THEN 'SEGMENTO B' " +
                        "        WHEN e.salary >= 8000 THEN 'SEGMENTO C' " +
                        "    END AS SalarySegment, " +
                        "    COUNT(*) AS EmployeeCount " +
                        "FROM employees e " +
                        "INNER JOIN departments d ON e.department_id = d.department_id " +
                        "GROUP BY d.department_name, SalarySegment " +
                        "ORDER BY d.department_name, SalarySegment", nativeQuery = true)
        List<Object[]> getSalarySegmentByDepartment();

        @Query(value = "SELECT " +
                        "    d.department_name as DepartmentName, " +
                        "    d.DEPARTMENT_ID as DepartmentId, " +
                        "    e.EMPLOYEE_ID as EmployeeId, " +
                        "    e.first_name as FirstName, " +
                        "    e.last_name as LastName, " +
                        "    e.EMAIL as Email, " +
                        "    e.PHONE_NUMBER as PhoneNumber, " +
                        "    e.salary as Salary " +
                        "FROM employees e " +
                        "INNER JOIN departments d ON e.department_id = d.department_id " +
                        "WHERE (e.department_id, e.salary) IN ( " +
                        "    SELECT department_id, MAX(salary) " +
                        "    FROM employees " +
                        "    GROUP BY department_id " +
                        ") " +
                        "ORDER BY d.department_name", nativeQuery = true)
        List<Object[]> getHighestPaidEmployeePerDepartment();

        @Query(value = "SELECT " +
                        "    e.employee_id, " +
                        "    e.first_name, " +
                        "    e.last_name, " +
                        "    e.hire_date, " +
                        "    d.department_name, " +
                        "    j.job_title " +
                        "FROM employees e " +
                        "INNER JOIN departments d ON e.employee_id = d.manager_id " +
                        "INNER JOIN jobs j ON e.job_id = j.job_id " +
                        "WHERE YEAR(e.hire_date) < YEAR(NOW()) - 15 " +
                        "ORDER BY e.hire_date", nativeQuery = true)
        List<Object[]> getVeteranManagers();

        @Query(value = "SELECT " +
                        "    d.department_id AS DepartmentId, " +
                        "    d.department_name AS DepartmentName, " +
                        "    ROUND(AVG(e.salary), 2) AS AverageSalary, " +
                        "    COUNT(e.employee_id) AS EmployeeCount " +
                        "FROM departments d " +
                        "INNER JOIN employees e ON d.department_id = e.department_id " +
                        "GROUP BY d.department_id, d.department_name " +
                        "HAVING COUNT(e.employee_id) > 10 " +
                        "ORDER BY AverageSalary DESC", nativeQuery = true)
        List<Object[]> getDepartmentsWithMoreThan10Employees();

        @Query(value = "SELECT " +
                        "    c.country_name AS CountryName, " +
                        "    COUNT(e.employee_id) AS EmployeeCount, " +
                        "    ROUND(AVG(e.salary), 2) AS AverageSalary, " +
                        "    MAX(e.salary) AS HighestSalary, " +
                        "    MIN(e.salary) AS LowestSalary, " +
                        "    ROUND(AVG(TIMESTAMPDIFF(YEAR, e.HIRE_DATE, CURRENT_DATE)), 2) AS AverageYearsOfService " +
                        "FROM employees e " +
                        "INNER JOIN departments d ON e.department_id = d.department_id " +
                        "INNER JOIN locations l ON d.location_id = l.location_id " +
                        "INNER JOIN countries c ON l.country_id = c.country_id " +
                        "LEFT JOIN job_history jh ON e.employee_id = jh.employee_id " +
                        "WHERE jh.employee_id IS NULL " +
                        "GROUP BY c.country_name " +
                        "ORDER BY EmployeeCount DESC", nativeQuery = true)
        List<Object[]> getCountryEmployeeStatistics();
}
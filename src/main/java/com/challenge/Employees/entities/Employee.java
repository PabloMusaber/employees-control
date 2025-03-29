package com.challenge.Employees.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    private Job job;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "commission_pct")
    private Double commissionPct;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;
}
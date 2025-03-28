package com.challenge.Employees.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "job_history")
public class JobHistory {
    @EmbeddedId
    private JobHistoryId id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}

@Embeddable
@Data
class JobHistoryId {
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "start_date")
    private LocalDate startDate;
}
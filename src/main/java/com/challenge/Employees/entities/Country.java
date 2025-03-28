package com.challenge.Employees.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "country_id", length = 2)
    private String countryId;

    @Column(name = "country_name")
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
}
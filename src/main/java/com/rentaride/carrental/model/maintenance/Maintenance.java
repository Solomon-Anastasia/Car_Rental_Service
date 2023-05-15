package com.rentaride.carrental.model.maintenance;

import com.rentaride.carrental.model.car.Car;
import com.rentaride.carrental.model.employee.Employee;

import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Maintenance {
    @Setter(AccessLevel.NONE)
    @Id
    @SequenceGenerator(name = "maintenance_sequence", sequenceName = "maintenance_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maintenance_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Car car;

    @Column(nullable = false)
    private LocalDate maintenanceStartDate;

    @Column(nullable = false)
    private LocalDate maintenanceEndDate;

    private String description;
}

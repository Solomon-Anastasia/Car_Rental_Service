package com.rentaride.carrental.model.employee;

import com.rentaride.carrental.model.maintenance.Maintenance;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Employee {
    @Setter(AccessLevel.NONE)
    @Id
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeePosition position;

    @Column(nullable = false)
    private String address;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Maintenance> maintenances;

    public Employee(String firstName, String lastName, EmployeePosition position, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.address = address;
        this.email = email;
    }
}

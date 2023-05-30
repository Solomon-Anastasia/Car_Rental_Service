package com.rentaride.carrental.model.car;

import com.rentaride.carrental.model.maintenance.Maintenance;
import com.rentaride.carrental.model.rental.Rental;

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
public class Car {
    @Setter(AccessLevel.NONE)
    @Id
    @SequenceGenerator(name = "car_sequence", sequenceName = "car_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_sequence")
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false, unique = true)
    private String licencePlate;

    @Column(nullable = false)
    private int rentalPricePerDay;

    @Column(nullable = false)
    private CarStatus status;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Rental> rentals;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Maintenance> maintenances;

    public Car(String make, String model, int year, String color, String licencePlate, int rentalPricePerDay, CarStatus status) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.licencePlate = licencePlate;
        this.rentalPricePerDay = rentalPricePerDay;
        this.status = status;
    }
}


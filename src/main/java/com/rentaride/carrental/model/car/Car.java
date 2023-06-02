package com.rentaride.carrental.model.car;

import com.rentaride.carrental.model.rental.Rental;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

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
    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Rental> rentals;

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


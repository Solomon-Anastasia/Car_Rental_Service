package com.rentaride.carrental.model.rental;

import com.rentaride.carrental.model.car.Car;

import com.rentaride.carrental.model.customer.Customer;
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
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Rental {
    @Setter(AccessLevel.NONE)
    @Id
    @SequenceGenerator(name = "rental_sequence", sequenceName = "rental_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private LocalDate rentalStartDate;

    @Column(nullable = false)
    private LocalDate rentalEndDate;

    @Column(nullable = false)
    private double totalCost;

    @Column(nullable = false)
    private RentalStatus rentalStatus;

    public Rental(Car car, Customer customer, LocalDate rentalStartDate, LocalDate rentalEndDate) {
        this.car = car;
        this.customer = customer;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.rentalStatus = calculateRentalStatus();
        this.totalCost = calculateTotalCost();
    }

    private RentalStatus calculateRentalStatus() {
        return (getRentalEndDate().isAfter(LocalDate.now())) ?
                RentalStatus.RETURNED : RentalStatus.ACTIVE;
    }

    public double calculateTotalCost() {
        return ChronoUnit.DAYS.between(getRentalStartDate(), getRentalEndDate()) * car.getRentalPricePerDay();
    }
}


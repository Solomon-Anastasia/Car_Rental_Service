package com.rentaride.carrental.service;

import com.rentaride.carrental.model.car.Car;
import com.rentaride.carrental.model.car.CarStatus;
import com.rentaride.carrental.model.rental.Rental;
import com.rentaride.carrental.model.rental.RentalStatus;
import com.rentaride.carrental.repository.CarRepository;

import com.rentaride.carrental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final RentalRepository rentalRepository;

    @Autowired
    public CarService(CarRepository carRepository, RentalRepository rentalRepository) {
        this.carRepository = carRepository;
        this.rentalRepository = rentalRepository;
    }

    public List<Car> getAllCars() {
        return (List<Car>) carRepository.findAll();
    }

//    TODO: Add verification for maintenance
    public List<Car> getAllAvailableCars() {
        List<Car> availableCars = getAllCars();
        List<Rental> rentals = (List<Rental>) rentalRepository.findAll();

        rentals.forEach(rental -> {
            if (rental.getRentalStatus() == RentalStatus.ACTIVE) {
                Car car = rental.getCar();
                car.setStatus(
                        rental.getRentalEndDate().isAfter(LocalDate.now()) ?
                                CarStatus.RENTED : CarStatus.AVAILABLE);
                carRepository.save(car);
            }
        });

        return availableCars.stream()
                .filter(car -> car.getStatus() == CarStatus.AVAILABLE)
                .collect(Collectors.toList());
    }

    public Car findCarByCarMakeAndCarModel(String carMakeModel) {
        String[] parts = carMakeModel.split(" ");
        return carRepository.findByMakeAndModel(parts[0], parts[1]);
    }

    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public long countCars() {
        return carRepository.count();
    }
}

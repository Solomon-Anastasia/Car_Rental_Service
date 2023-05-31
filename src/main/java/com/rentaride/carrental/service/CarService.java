package com.rentaride.carrental.service;

import com.rentaride.carrental.model.car.Car;
import com.rentaride.carrental.model.car.CarStatus;
import com.rentaride.carrental.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    public List<Car> getAllAvailableCars() {
        List<Car> availableCars = getAllCars();

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

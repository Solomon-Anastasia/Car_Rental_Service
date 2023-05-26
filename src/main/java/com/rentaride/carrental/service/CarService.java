package com.rentaride.carrental.service;

import com.rentaride.carrental.model.car.Car;
import com.rentaride.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

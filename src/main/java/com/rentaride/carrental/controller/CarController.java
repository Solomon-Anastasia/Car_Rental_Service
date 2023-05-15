package com.rentaride.carrental.controller;

import com.rentaride.carrental.model.car.Car;
import com.rentaride.carrental.service.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping({"/{car_id}"})
    public Optional<Car> getCarById(@PathVariable("car_id") Long id) {
        return carService.getCarById(id);
    }
}

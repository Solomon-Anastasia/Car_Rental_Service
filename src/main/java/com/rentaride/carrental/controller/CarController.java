package com.rentaride.carrental.controller;

import com.rentaride.carrental.model.car.Car;
import com.rentaride.carrental.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

   /* @GetMapping({"/{car_id}"})
    public Optional<Car> getCarById(@PathVariable("car_id") Long id) {
        return carService.getCarById(id);
    }
    }*/
   @GetMapping("/rent")
   public String showRentCarPage(Model model) {
       List<Car> cars = carService.getAllCars();
       model.addAttribute("cars", cars);

       return "rent";
   }
}

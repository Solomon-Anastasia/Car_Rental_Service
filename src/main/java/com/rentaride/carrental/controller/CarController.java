package com.rentaride.carrental.controller;

import com.rentaride.carrental.model.appuser.AppUser;
import com.rentaride.carrental.model.car.Car;
import com.rentaride.carrental.model.dto.RegistrationRequestDto;
import com.rentaride.carrental.model.dto.RentalDto;
import com.rentaride.carrental.service.CarService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/rent")
    public String showRentCarPage(Model model, Authentication authentication) {
        List<Car> cars = carService.getAllAvailableCars();
        model.addAttribute("cars", cars);

        if (cars.isEmpty()) {
            Map<String, Object> nextCarData = carService.getNextAvailableCar();

            Car nextCar = (Car) nextCarData.get("car");
            LocalDate rentalEndDate = (LocalDate) nextCarData.get("rentalEndDate");
            List<RentalDto> remainingAvailableRents = carService
                    .getRemainingActiveRentals()
                    .stream()
                    .map(rental ->
                            new RentalDto(rental.getCar(), (int) LocalDate.now().until(rental.getRentalEndDate(), ChronoUnit.DAYS)
                    ))
                    .collect(Collectors.toList());

            model.addAttribute("nextAvailableCar", nextCar);
            model.addAttribute("remainingDays", LocalDate.now().until(rentalEndDate, ChronoUnit.DAYS));
            model.addAttribute("remainingAvailableRentals", remainingAvailableRents);
        }

        if (authentication != null && authentication.getPrincipal() instanceof AppUser appUser) {
            model.addAttribute("currentUser", appUser);
        } else {
            model.addAttribute("guestUser",
                    new RegistrationRequestDto(
                            "First Name",
                            "Last Name",
                            "Address",
                            "guestEmail@gmail.com",
                            "GuestPassword"));
        }

        return "rent";
    }
}

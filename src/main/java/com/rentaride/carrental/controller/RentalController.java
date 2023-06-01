package com.rentaride.carrental.controller;

import com.rentaride.carrental.model.appuser.AppUser;
import com.rentaride.carrental.model.car.Car;
import com.rentaride.carrental.model.car.CarStatus;
import com.rentaride.carrental.model.rental.Rental;

import com.rentaride.carrental.model.rental.RentalStatus;
import com.rentaride.carrental.service.CarService;
import com.rentaride.carrental.service.RentalService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class RentalController {
    private final RentalService rentalService;
    private final CarService carService;

    public RentalController(RentalService rentalService, CarService carService) {
        this.rentalService = rentalService;
        this.carService = carService;
    }

    @GetMapping("/rentedCars")
    public String showRentedCarsPage(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof AppUser appUser) {
            List<Rental> rentedCars = rentalService.getRentalsByUserId(appUser.getId());
            model.addAttribute("rentalCars", rentedCars);

            return "rentedCars";
        }
        return "redirect:/login";
    }

    @GetMapping("/cancelRent/{carPlate}")
    public String cancelRenting(@PathVariable("carPlate") String carPlate) {
        Optional<Rental> optionalRental = rentalService.getRentalByCarLicencePlate(carPlate);

        if (optionalRental.isPresent() && optionalRental.get().getRentalStatus().equals(RentalStatus.ACTIVE)) {
            Rental rental = optionalRental.get();

            rental.setRentalStatus(RentalStatus.CANCELED);
            Car car = rental.getCar();
            car.setStatus(CarStatus.AVAILABLE);
            rentalService.saveRental(rental);
            carService.saveCar(car);
        }

        return "redirect:/rentedCars";
    }
}

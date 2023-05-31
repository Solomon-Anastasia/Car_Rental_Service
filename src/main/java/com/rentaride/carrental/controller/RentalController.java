package com.rentaride.carrental.controller;

import com.rentaride.carrental.model.appuser.AppUser;
import com.rentaride.carrental.model.rental.Rental;

import com.rentaride.carrental.service.RentalService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
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
}

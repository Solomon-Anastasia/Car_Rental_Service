package com.rentaride.carrental.controller;

import com.rentaride.carrental.model.appuser.AppUser;
import com.rentaride.carrental.model.dto.RentRequestDto;
import com.rentaride.carrental.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class ReservationController {
    private final CarService carService;

    @PostMapping("/reservation")
    public String showReservationPage(RentRequestDto rentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AppUser) {
            System.out.println(rentRequest.getFirstName());
            System.out.println(rentRequest.getLastName());
            System.out.println(rentRequest.getEmail());
            System.out.println(rentRequest.getAddress());
            System.out.println(rentRequest.getCarPricePerDay());
            System.out.println(rentRequest.getStartDate());
            System.out.println(rentRequest.getEndDate());


            return "redirect:about";
        }

        return "redirect:login";
    }

//    TODO: Create or a page or something to be able to se the reservation, also, insert into the database values
    @PostMapping("/successfulReservation")
    public String successfulReservation() {
        /*Car car = carService.findCarByCarMakeAndCarModel(carModel);
        if (car != null) {
            car.setStatus(CarStatus.RENTED);
            carService.saveCar(car);
        }*/

        return "redirect:about";
    }
}

package com.rentaride.carrental.controller;

import com.rentaride.carrental.model.appuser.AppUser;
import com.rentaride.carrental.model.car.Car;
import com.rentaride.carrental.model.car.CarStatus;
import com.rentaride.carrental.model.customer.Customer;
import com.rentaride.carrental.model.dto.RentRequestDto;
import com.rentaride.carrental.model.rental.Rental;
import com.rentaride.carrental.service.AppUserService;
import com.rentaride.carrental.service.CarService;
import com.rentaride.carrental.service.CustomerService;
import com.rentaride.carrental.service.RentalConfirmationService;
import com.rentaride.carrental.service.RentalService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class ReservationController {
    private final CarService carService;
    private final CustomerService customerService;
    private final AppUserService appUserService;
    private final RentalService rentalService;
    private final RentalConfirmationService rentalConfirmationService;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping("/reservation")
    public String reserveARent(RentRequestDto rentRequest, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof AppUser) {
            Car car = carService.findCarByCarMakeAndCarModel(rentRequest.getCarModel());
            car.setStatus(CarStatus.RENTED);
            carService.saveCar(car);

            Optional<AppUser> optionalAppUser = appUserService
                    .findByEmail(((AppUser) authentication.getPrincipal()).getEmail());
            AppUser appUser = optionalAppUser.get();
            AppUser mergedAppUser = entityManager.merge(appUser);

            Customer customer = new Customer(mergedAppUser);
            customerService.saveCustomer(customer);

            Rental rental = new Rental(
                    car,
                    customer,
                    LocalDate.parse(rentRequest.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalDate.parse(rentRequest.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );

            rentalService.saveRental(rental);

            rentalConfirmationService.send(
                    appUser.getEmail(),
                    rentalService.sendRentalConfirmationEmail(
                            appUser.getFirstName(),
                            car.getMake(),
                            car.getModel(),
                            rental.getTotalCost(),
                            rental.getRentalStartDate().toString(),
                            rental.getRentalEndDate().toString()
                    )
            );
            return "redirect:rentedCars";
        }

        return "redirect:login";
    }
}

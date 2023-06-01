package com.rentaride.carrental.service;

import com.rentaride.carrental.model.rental.Rental;
import com.rentaride.carrental.repository.RentalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Transactional
    public void saveRental(Rental rental) {
        rentalRepository.save(rental);
    }

    @Transactional
    public List<Rental> getRentalsByUserId(Long userId) {
        return rentalRepository.findRentalsByUserId(userId);
    }

    public Optional<Rental> getRentalByCarLicencePlate(String carPlate) {
        return rentalRepository.findByCarLicencePlate(carPlate);
    }
}

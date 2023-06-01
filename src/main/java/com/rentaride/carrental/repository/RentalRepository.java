package com.rentaride.carrental.repository;

import com.rentaride.carrental.model.rental.Rental;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
    @Query("SELECT r FROM Rental r WHERE r.customer.appUser.id = :userId AND r.rentalStatus = 'ACTIVE'")
    List<Rental> findRentalsByUserId(@Param("userId") Long userId);

    @Query("SELECT r FROM Rental r WHERE r.car.licencePlate = :carPlate AND r.rentalStatus = 'ACTIVE'")
    Optional<Rental> findByCarLicencePlate(String carPlate);

    @Query("SELECT r FROM Rental r WHERE r.rentalStatus = 'ACTIVE'")
    List<Rental> findAllActive();
}

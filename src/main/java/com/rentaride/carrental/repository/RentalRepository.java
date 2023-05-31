package com.rentaride.carrental.repository;

import com.rentaride.carrental.model.rental.Rental;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
    @Query("SELECT r FROM Rental r WHERE r.customer.appUser.id = :userId")
    List<Rental> findRentalsByUserId(@Param("userId") Long userId);
}

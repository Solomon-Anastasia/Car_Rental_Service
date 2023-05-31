package com.rentaride.carrental.repository;

import com.rentaride.carrental.model.rental.Rental;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
}

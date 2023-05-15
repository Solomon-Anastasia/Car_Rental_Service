package com.rentaride.carrental.repository;

import com.rentaride.carrental.model.car.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
}

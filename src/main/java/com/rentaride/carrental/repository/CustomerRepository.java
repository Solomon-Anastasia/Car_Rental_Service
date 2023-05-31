package com.rentaride.carrental.repository;

import com.rentaride.carrental.model.customer.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>  {
}

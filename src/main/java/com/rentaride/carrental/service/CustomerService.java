package com.rentaride.carrental.service;

import com.rentaride.carrental.model.customer.Customer;
import com.rentaride.carrental.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}

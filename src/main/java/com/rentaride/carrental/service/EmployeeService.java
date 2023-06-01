package com.rentaride.carrental.service;

import com.rentaride.carrental.model.employee.Employee;
import com.rentaride.carrental.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Long countEmployees() {
        return employeeRepository.count();
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}

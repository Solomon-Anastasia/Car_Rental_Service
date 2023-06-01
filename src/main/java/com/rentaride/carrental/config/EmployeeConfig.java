package com.rentaride.carrental.config;

import com.rentaride.carrental.model.employee.Employee;
import com.rentaride.carrental.model.employee.EmployeePosition;
import com.rentaride.carrental.service.EmployeeService;

import com.opencsv.CSVReader;

import lombok.SneakyThrows;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;

import java.util.List;

@Configuration
public class EmployeeConfig {
    @SneakyThrows
    @Bean
    public CommandLineRunner employeeInitialize(EmployeeService employeeService) {
        return args -> {
            if (employeeService.countEmployees() == 0) {
                CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/static/csv/employee.csv"));
                List<String[]> csvData = csvReader.readAll();
                csvReader.close();

                for (int i = 1; i < csvData.size(); i++) {
                    String[] employeeData = csvData.get(i);
                    employeeService.saveEmployee(
                            new Employee(
                                    employeeData[0],
                                    employeeData[1],
                                    EmployeePosition.valueOf(employeeData[2]),
                                    employeeData[3],
                                    employeeData[4]
                            )
                    );
                }
            }
        };
    }
}

package com.rentaride.carrental.config;

import com.rentaride.carrental.model.car.Car;
import com.rentaride.carrental.model.car.CarStatus;
import com.rentaride.carrental.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CarConfig {
//    @Bean
//    public CommandLineRunner commandLineRunner(CarRepository carRepository) {
//        return args -> carRepository.saveAll(List.of(
//                new Car("Toyota", "Corolla", 2021, "Red", "MDT 654", 50, CarStatus.AVAILABLE),
//                new Car("Ford", "Mustang", 2022, "Black", "KRT 631", 150, CarStatus.AVAILABLE)
//        ));
//    }
}

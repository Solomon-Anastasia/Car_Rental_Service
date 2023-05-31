package com.rentaride.carrental.config;

import com.opencsv.CSVReader;
import com.rentaride.carrental.model.car.Car;
import com.rentaride.carrental.model.car.CarStatus;
import com.rentaride.carrental.service.CarService;

import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.util.List;

@Configuration
public class CarConfig {
    @SneakyThrows
    @Bean
    public CommandLineRunner commandLineRunner(CarService carService) {
        return args -> {
            if (carService.countCars() == 0) {
                CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/static/db/cars.csv"));
                List<String[]> csvData = csvReader.readAll();
                csvReader.close();

                for (int i = 1; i < csvData.size(); i++) {
                    String[] carData = csvData.get(i);
                    carService.saveCar(
                            new Car(
                                    carData[0],
                                    carData[1],
                                    Integer.parseInt(carData[2]),
                                    carData[3],
                                    carData[4],
                                    Integer.parseInt(carData[5]),
                                    CarStatus.valueOf(carData[6])
                            )
                    );
                }
            }
        };
    }
}

package com.rentaride.carrental.model.dto;

import com.rentaride.carrental.model.car.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RentalDto {
    private Car car;
    private int remainingDays;
}

package com.rentaride.carrental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentRequestDto {
    private String carModel;
    private String startDate;
    private String endDate;
}

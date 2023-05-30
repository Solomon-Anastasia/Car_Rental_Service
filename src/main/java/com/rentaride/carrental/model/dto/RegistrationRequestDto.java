package com.rentaride.carrental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationRequestDto {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
}

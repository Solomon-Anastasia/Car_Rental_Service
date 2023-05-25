package com.rentaride.carrental.registration;

public record RegistrationRequest(String firstName, String lastName, String address, String email, String password) {
}

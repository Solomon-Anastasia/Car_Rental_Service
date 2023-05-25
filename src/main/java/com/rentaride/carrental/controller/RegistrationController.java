package com.rentaride.carrental.controller;

import com.rentaride.carrental.registration.RegistrationRequest;
import com.rentaride.carrental.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
   @PostMapping("/register")
   public String register(@RequestBody RegistrationRequest request) {
       return registrationService.register(request);
   }

   @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
}

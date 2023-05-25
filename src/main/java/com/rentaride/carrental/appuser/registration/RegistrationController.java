package com.rentaride.carrental.appuser.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
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

   /*@GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }*/
}

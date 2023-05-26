package com.rentaride.carrental.service;

import com.rentaride.carrental.model.appuser.AppUser;
import com.rentaride.carrental.model.appuser.AppUserRole;
import com.rentaride.carrental.registration.EmailValidator;
import com.rentaride.carrental.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

   public String register(RegistrationRequest request) {
       boolean isValidEmail = emailValidator.test(request.getEmail());

       if (!isValidEmail) {
           throw new IllegalStateException("email not valid");
       }

       return appUserService.signUpUser(
               new AppUser(
                       request.getFirstName(),
                       request.getLastName(),
                       request.getAddress(),
                       request.getEmail(),
                       request.getPassword(),
                       AppUserRole.USER
               )
       );
   }
}

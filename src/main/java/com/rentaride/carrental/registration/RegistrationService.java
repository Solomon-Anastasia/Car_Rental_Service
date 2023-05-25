package com.rentaride.carrental.registration;

import com.rentaride.carrental.model.appuser.AppUser;
import com.rentaride.carrental.model.appuser.AppUserRole;
import com.rentaride.carrental.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.email());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        return appUserService.signUpUser(
                new AppUser(
                        request.firstName(),
                        request.lastName(),
                        request.address(),
                        request.email(),
                        request.password(),
                        AppUserRole.USER
                )
        );
    }
}

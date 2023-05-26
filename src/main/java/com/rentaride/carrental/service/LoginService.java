package com.rentaride.carrental.service;

import com.rentaride.carrental.model.appuser.AppUser;
import com.rentaride.carrental.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean isValidCredentials(String email, String password) {
        if (appUserRepository.findByEmail(email).isPresent()) {
            AppUser validUser = appUserRepository.findByEmail(email).get();

            return  bCryptPasswordEncoder.matches(password, validUser.getPassword());
        }
        return false;
    }
}
